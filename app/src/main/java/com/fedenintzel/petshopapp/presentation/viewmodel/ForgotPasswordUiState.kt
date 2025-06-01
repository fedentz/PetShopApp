package com.fedenintzel.petshopapp.presentation.viewmodel

enum class ForgotPasswordStep {
    ENTER_EMAIL,
    RESET_PASSWORD
}

data class ForgotPasswordUiState(
    val step: ForgotPasswordStep = ForgotPasswordStep.ENTER_EMAIL,
    val email: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)
