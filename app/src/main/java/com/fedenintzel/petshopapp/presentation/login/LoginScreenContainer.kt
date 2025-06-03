package com.fedenintzel.petshopapp.presentation.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel

/**
 * Container que conecta el LoginViewModel con la UI de LoginScreen.
 * Encapsula la navegación y acciones del login.
 */
@Composable
fun LoginScreenContainer(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.value

    LoginScreen(
        uiState = uiState,

        // Callback cuando el usuario presiona "Get Started"
        onLoginClick = { username, password ->
            viewModel.login(username, password)
        },

        // Callback para navegar a la pantalla de registro
        onCreateAccountClick = {
            navController.navigate("register")
        },

        // 🆕 Callback para ir a forgot password
        onForgotPasswordClick = {
            navController.navigate("forgot")
        },

        // Si el login fue exitoso, navegar a la home
        onSuccessLogin = {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    )
}
