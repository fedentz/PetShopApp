package com.fedenintzel.petshopapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.domain.usecase.GetCartUseCase
import com.fedenintzel.petshopapp.presentation.screen.cart.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsable de manejar el estado del carrito.
 * Se comunica con el UseCase para obtener los datos y expone
 * un estado observable por la UI.
 */
@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CartUiState())
    val state: State<CartUiState> = _state

    init {
        loadCart()
    }

    private fun loadCart() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val cart = getCartUseCase()
                _state.value = _state.value.copy(cart = cart)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message ?: "Error desconocido")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}




