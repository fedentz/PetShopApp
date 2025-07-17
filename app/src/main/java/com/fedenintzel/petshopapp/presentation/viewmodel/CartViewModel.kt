package com.fedenintzel.petshopapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fedenintzel.petshopapp.presentation.screen.cart.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.data.mapper.toCartItem
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.model.Product
import com.fedenintzel.petshopapp.domain.usecase.AddItemToCartUseCase
import com.fedenintzel.petshopapp.domain.usecase.ClearCartUseCase
import com.fedenintzel.petshopapp.domain.usecase.GetCartUseCase
import com.fedenintzel.petshopapp.domain.usecase.GetPurchaseHistoryUseCase
import com.fedenintzel.petshopapp.domain.usecase.RemoveItemFromCartUseCase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsable de manejar el estado del carrito.
 * Se comunica con el UseCase para obtener los datos y expone
 * un estado observable por la UI.
 */
@HiltViewModel
class CartViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val getCartUseCase: GetCartUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val getPurchaseHistoryUseCase: GetPurchaseHistoryUseCase


) : ViewModel() {

    var purchaseHistory by mutableStateOf<List<Cart>>(emptyList())
        private set

    var isLoadingHistory by mutableStateOf(false)
        private set

    private val _state = mutableStateOf(CartUiState())


    val state: State<CartUiState> = _state

    private val _showSnackbar = mutableStateOf(false)
    val showSnackbar: State<Boolean> = _showSnackbar

    init {
        loadCart()
    }


    fun loadPurchaseHistory(userId: String) {
        viewModelScope.launch {
            println(" [ViewModel] Cargando historial para: $userId")
            isLoadingHistory = true
            try {
                val result = getPurchaseHistoryUseCase(userId)
                println(" [ViewModel] Carritos obtenidos: ${result.size}")
                purchaseHistory = result
            } catch (e: Exception) {
                println(" [ViewModel] Error: ${e.message}")
                purchaseHistory = emptyList()
            }
            isLoadingHistory = false
        }
    }

    fun guardarCarritoEnFirestore(cart: Cart) {
        val uid = auth.currentUser?.uid ?: return

        val carritoMap = mapOf(
            "uid" to uid,
            "finalizado" to false,
            "total" to cart.total,
            "discountedTotal" to cart.discountedTotal,
            "totalProducts" to cart.totalProducts,
            "totalQuantity" to cart.totalQuantity,
            "products" to cart.products.map { it.copy() },
            "timestamp" to FieldValue.serverTimestamp()
        )

        firestore.collection("carritos")
            .add(carritoMap)
            .addOnSuccessListener {
                Log.d("Firestore", "Carrito guardado con éxito para UID: $uid")
                _state.value = _state.value.copy(carritoGuardado = true)
                limpiarCarrito()
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error al guardar carrito", e)
            }
    }


    fun resetSnackbar() {
        _state.value = _state.value.copy(carritoGuardado = false)
    }

    fun limpiarCarrito() {
        _state.value = _state.value.copy(
            cart = Cart(
                id = 1,
                products = emptyList(),
                total = 0.0,
                discountedTotal = 0.0,
                totalProducts = 0,
                totalQuantity = 0,
                userId = ""
            )
        )

        viewModelScope.launch {
            clearCartUseCase()
        }
    }

    fun loadCart() {
        viewModelScope.launch {
            try {
                val uid = auth.currentUser?.uid ?: return@launch
                val cartItems = getCartUseCase()

                val cart = if (cartItems.isNotEmpty()) {
                    Cart(
                        id = 1,
                        products = cartItems,
                        total = cartItems.sumOf { it.price },
                        discountedTotal = cartItems.sumOf { it.price },
                        totalProducts = cartItems.sumOf { it.quantity },
                        totalQuantity = cartItems.sumOf { it.quantity },
                        userId = uid
                    ).also {
                        Log.d("CartViewModel", "loadCart() - Se recuperó carrito con ${cartItems.size} productos")
                    }
                } else {
                    Cart(
                        id = 1,
                        products = emptyList(),
                        total = 0.0,
                        discountedTotal = 0.0,
                        totalProducts = 0,
                        totalQuantity = 0,
                        userId = uid
                    ).also {
                        Log.d("CartViewModel", "loadCart() - No había carrito previo, inicializado vacío.")
                    }
                }

                _state.value = _state.value.copy(cart = cart)

            } catch (e: Exception) {
                Log.e("CartViewModel", "Error al cargar el carrito", e)
            }
        }
    }



    fun removeFromCart(productId: Int) {
        val currentCart = _state.value.cart ?: return
        val updatedProducts = currentCart.products.filter { it.id != productId }

        val updatedCart = currentCart.copy(
            products = updatedProducts,
            totalProducts = updatedProducts.sumOf { it.quantity },
            total = updatedProducts.sumOf { it.price },
            discountedTotal = updatedProducts.sumOf { it.price }
        )

        _state.value = _state.value.copy(cart = updatedCart)

        viewModelScope.launch {
            removeItemFromCartUseCase(productId)
        }
    }

    fun addToCart(product: Product) {
        Log.d("DEBUG_ADD", "Agregando producto al carrito: $product")
        val uid = auth.currentUser?.uid ?: return
        val currentCart = _state.value.cart
        val currentProducts = currentCart?.products?.toMutableList() ?: mutableListOf()

        val existing = currentProducts.find { it.id == product.id }

        if (existing != null) {
            val updated = existing.copy(
                quantity = existing.quantity + 1,
                price = existing.price + product.price
            )
            val index = currentProducts.indexOf(existing)
            currentProducts[index] = updated
        } else {
            currentProducts.add(product.copy(quantity = 1).toCartItem())
        }

        val updatedTotal = currentProducts.sumOf { it.price }

        val updatedCart = currentCart?.copy(
            products = currentProducts,
            totalProducts = currentProducts.sumOf { it.quantity },
            totalQuantity = currentProducts.sumOf { it.quantity },
            total = updatedTotal,
            discountedTotal = updatedTotal,
            userId = uid
        ) ?: Cart(
            id = 1,
            products = currentProducts,
            total = updatedTotal,
            discountedTotal = updatedTotal,
            totalProducts = currentProducts.sumOf { it.quantity },
            totalQuantity = currentProducts.sumOf { it.quantity },
            userId = uid
        )

        _state.value = _state.value.copy(cart = updatedCart)

        viewModelScope.launch {
            val item = updatedCart.products.find { it.id == product.id }
            if (item != null) {
                addItemToCartUseCase(item)
            }
        }

        notifyAddedToCart()
    }


    fun notifyAddedToCart() {
        _showSnackbar.value = true
    }

    fun clearSnackbar() {
        _showSnackbar.value = false
    }
}
