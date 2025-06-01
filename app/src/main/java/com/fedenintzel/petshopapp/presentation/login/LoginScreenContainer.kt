package com.fedenintzel.petshopapp.presentation.login

import androidx.compose.runtime.Composable
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel

/**
 * Container que expone directamente loginViewModel.uiState (que es un Compose State)
 * y lo pasa a LoginScreen, junto con las lambdas de acción.
 */
@Composable
fun LoginScreenContainer(
    loginViewModel: LoginViewModel,
    onLoginClick: (String, String) -> Unit,
    onCreateAccountClick: () -> Unit
) {
    // 1) Leemos directamente el uiState que ya es un State<LoginUiState>
    val loginUiState: LoginUiState = loginViewModel.uiState

    // 2) Llamamos a la pantalla composable pasando uiState y las actions
    LoginScreen(
        uiState = loginUiState,
        onLoginClick = { email, password ->
            onLoginClick(email, password)
        },
        onCreateAccountClick = {
            onCreateAccountClick()
        }
    )
}
