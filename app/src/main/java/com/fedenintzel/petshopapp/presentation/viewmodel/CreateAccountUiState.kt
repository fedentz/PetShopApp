package com.fedenintzel.petshopapp.presentation.viewModel

/**
 * Estado que expone el CreateAccountViewModel a la UI.
 *
 * @param isLoading indica si la petición de registro está en progreso
 * @param errorMessage mensaje de error (si ocurrió)
 * @param isSuccess si el registro fue exitoso
 * @param firstName, lastName, username, email, password inputs de la pantalla
 * @param agreed si el usuario marcó “Acepto términos”
 */
data class CreateAccountUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val agreed: Boolean = false
)
