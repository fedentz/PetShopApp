package com.fedenintzel.petshopapp.presentation.screen.cart

import com.fedenintzel.petshopapp.domain.model.Cart

data class CartUiState(
    val cart: Cart? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)