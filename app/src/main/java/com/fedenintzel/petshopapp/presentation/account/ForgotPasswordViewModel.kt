package com.fedenintzel.petshopapp.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.data.repository.AuthRepository
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordStep
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordUiState
import com.fedenintzel.petshopapp.domain.usecase.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = newEmail,
            errorMessage = null,
            successMessage = null
        )
    }

    fun onNewPasswordChange(pw: String) {
        _uiState.value = _uiState.value.copy(
            newPassword = pw,
            errorMessage = null,
            successMessage = null
        )
    }

    fun onConfirmPasswordChange(pw: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = pw,
            errorMessage = null,
            successMessage = null
        )
    }

    /** Primer paso: enviar enlace de restablecimiento al email */
    fun sendResetLink(email1: String) {
        val currentEmail = _uiState.value.email.trim()
        if (currentEmail.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "El email no puede estar vacío")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null, successMessage = null)
            when (val resultado = authRepo.forgotPassword(currentEmail)) {
                is Result.Success<*> -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Revisa tu correo para restablecer contraseña",
                        step = ForgotPasswordStep.RESET_PASSWORD
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = resultado.message
                    )
                }

                Result.Loading -> TODO()
            }
        }
    }

    /** Segundo paso: restablecer la contraseña */
    fun resetPassword() {
        val state = _uiState.value
        val email = state.email.trim()
        val newPw = state.newPassword
        val confirmPw = state.confirmPassword

        // Validaciones básicas
        if (newPw.length < 6) {
            _uiState.value = state.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres")
            return
        }
        if (newPw != confirmPw) {
            _uiState.value = state.copy(errorMessage = "Las contraseñas no coinciden")
            return
        }

        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true, errorMessage = null, successMessage = null)
            when (val resultado = authRepo.resetPassword(email, newPw)) {
                is Result.Success<*> -> {
                    _uiState.value = state.copy(
                        isLoading = false,
                        successMessage = "Contraseña restablecida con éxito",
                        // Opcional: puedes navegar de regreso a Login desde la UI Container,
                        // o bien emitir un flag para cerrar esta pantalla.
                    )
                }
                is Result.Error -> {
                    _uiState.value = state.copy(
                        isLoading = false,
                        errorMessage = resultado.message
                    )
                }

                Result.Loading -> TODO()
            }
        }
    }

}
