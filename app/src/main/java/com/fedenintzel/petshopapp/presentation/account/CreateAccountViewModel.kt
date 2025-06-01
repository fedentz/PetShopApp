package com.fedenintzel.petshopapp.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.data.repository.AuthRepository
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de "Create Account".
 * Inyecta AuthRepository (puede ser manual o con Hilt).
 */
class CreateAccountViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    // Estado interno mutable
    private val _uiState = MutableStateFlow(CreateAccountUiState())
    // Exposición inmutable
    val uiState: StateFlow<CreateAccountUiState> = _uiState

    /**
     * Este método se dispara cuando el usuario toca "Get Started" en la pantalla de registro.
     * Valida localmente que los campos no estén vacíos, que se hayan aceptado términos, etc.
     * Luego llama al repositorio para registrar al usuario.
     */
    fun createAccount(
        fullName: String,
        email: String,
        password: String,
        agreed: Boolean
    ) {
        // Descomponer el nombre completo en primer y segundo nombre
        val parts = fullName.trim().split("\\s+".toRegex(), limit = 2)
        val firstName = parts.getOrNull(0) ?: ""
        val lastName = parts.getOrNull(1) ?: ""

        // Validaciones básicas
        if (firstName.isEmpty() || lastName.isEmpty()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Debe ingresar nombre y apellido"
            )
            return
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Email inválido"
            )
            return
        }
        if (password.length < 6) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "La contraseña debe tener al menos 6 caracteres"
            )
            return
        }
        if (!agreed) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Debes aceptar los términos"
            )
            return
        }

        // Si todo está OK, iniciamos loading y llamamos al repositorio
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val response = authRepository.register(
                    firstName = firstName,
                    lastName = lastName,
                    username = email.substringBefore("@"), // usar parte antes de @ como username
                    email = email,
                    password = password
                )
                // Si no arroja excepción, se asume éxito
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                // Capturar cualquier excepción y mostrar mensaje de error
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error desconocido"
                )
            }
        }
    }

    /**
     * Factory para instanciar CreateAccountViewModel sin Hilt (inyección manual).
     */
    class Factory(
        private val authRepository: AuthRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreateAccountViewModel(authRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
