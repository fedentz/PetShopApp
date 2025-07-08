package com.fedenintzel.petshopapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.domain.usecase.GetCartUseCase
import com.fedenintzel.petshopapp.presentation.screen.cart.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.fedenintzel.petshopapp.data.mapper.toCartItem
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.model.Product
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
    private val getCartUseCase: GetCartUseCase,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _state = mutableStateOf(CartUiState())
    val state: State<CartUiState> = _state

    private val _showSnackbar = mutableStateOf(false)
    val showSnackbar: State<Boolean> = _showSnackbar

    init {
        loadCart()
    }

    fun guardarCarritoEnFirestore(cart: Cart) {
        firestore.collection("carritos")
            .add(cart)
            .addOnSuccessListener {
                Log.d("Firestore", "Carrito guardado con éxito.")
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


//    private fun loadCart() {
//        viewModelScope.launch {
//            _state.value = _state.value.copy(isLoading = true, error = null)
//            try {
//                val cart = getCartUseCase()
//                _state.value = _state.value.copy(cart = cart)
//            } catch (e: Exception) {
//                _state.value = _state.value.copy(error = e.message ?: "Error desconocido")
//            } finally {
//                _state.value = _state.value.copy(isLoading = false)
//            }
//        }
//    }

    fun limpiarCarrito() {
        _state.value = _state.value.copy(
            cart = Cart(
                id = 1,
                products = emptyList(),
                total = 0.0,
                discountedTotal = 0.0,
                totalProducts = 0,
                totalQuantity = 0,
                userId = 1
            )
        )
    }

    private fun loadCart() {
        _state.value = _state.value.copy(
            cart = Cart(
                id = 1,
                products = emptyList(),
                total = 0.0,
                discountedTotal = 0.0,
                totalProducts = 0,
                totalQuantity = 0,
                userId = 1
            )
        )
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
    }

    fun addToCart(product: Product) {
        Log.d("DEBUG_ADD", "Agregando producto al carrito: $product")
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
            total = updatedTotal,
            discountedTotal = updatedTotal
        ) ?: com.fedenintzel.petshopapp.domain.model.Cart(
            id = 1,
            products = currentProducts,
            total = updatedTotal,
            discountedTotal = updatedTotal,
            totalProducts = currentProducts.sumOf { it.quantity },
            userId = 1,
            totalQuantity = currentProducts.sumOf { it.quantity }
        )

        _state.value = _state.value.copy(cart = updatedCart)
        notifyAddedToCart()
    }

    fun notifyAddedToCart() {
        _showSnackbar.value = true
    }

    fun clearSnackbar() {
        _showSnackbar.value = false
    }
}
