package com.fedenintzel.petshopapp.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.domain.usecase.RegisterUseCase
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountUiState())
    val uiState: StateFlow<CreateAccountUiState> = _uiState

    fun createAccount(
        fullName: String,
        email: String,
        password: String,
        agreed: Boolean
    ) {
        // Validaciones básicas
        if (fullName.isBlank() || !fullName.contains(" ")) {
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

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val user = registerUseCase(
                    fullName = fullName,
                    email = email,
                    password = password
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error desconocido"
                )
            }
        }
    }
}
