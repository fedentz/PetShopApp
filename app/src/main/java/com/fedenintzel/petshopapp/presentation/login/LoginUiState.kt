package com.fedenintzel.petshopapp.presentation.login

import com.fedenintzel.petshopapp.domain.model.User

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val user: User? = null
)