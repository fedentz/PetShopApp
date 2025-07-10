package com.fedenintzel.petshopapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.domain.model.Product
import com.fedenintzel.petshopapp.domain.usecase.GetProductsUseCase
import com.fedenintzel.petshopapp.presentation.screen.products.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para manejar la lógica y el estado de la pantalla de productos.
 */

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val products = getProductsUseCase()
                _state.value = ProductState(products = products)
            } catch (e: Exception) {
                _state.value = ProductState(error = e.message)
            }
        }
    }

    fun addToCart(product: Product) {
        // TO-DO
    }
}
