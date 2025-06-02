package com.fedenintzel.petshopapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.domain.model.Product
import com.fedenintzel.petshopapp.domain.repository.FavoritesRepository
import com.fedenintzel.petshopapp.domain.usecase.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun fetchProductById(productId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val result = getProductByIdUseCase(productId)
                _product.value = result
                _isFavorite.value = favoritesRepository.isFavorite(productId)
            } catch (e: Exception) {
                _error.value = "Error al obtener producto: ${e.message}"
                _product.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite() {
        val product = _product.value ?: return
        viewModelScope.launch {
            favoritesRepository.toggleFavorite(product)
            _isFavorite.value = favoritesRepository.isFavorite(product.id)
        }
    }
}
