package com.fedenintzel.petshopapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.data.model.Product
import com.fedenintzel.petshopapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init { fetchProducts() }

    private fun fetchProducts() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                _uiState.value = _uiState.value.copy(products = response.products, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun getProductById(productId: Int): Product? {
        return uiState.value.products.find { it.id == productId }
    }

    fun addToCart(product: Product) {
        // TO-DO
    }
}

