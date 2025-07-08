package com.fedenintzel.petshopapp.presentation.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.SessionViewModel

/**
 * Container que conecta el LoginViewModel con la UI de LoginScreen.
 * Encapsula la navegación y acciones del login.
 */
@Composable
fun LoginScreenContainer(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    sessionViewModel: SessionViewModel
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
            navController.navigate(Destinations.CREATE_ACCOUNT)
        },

        //  Callback para ir a forgot password
        onForgotPasswordClick = {
            navController.navigate(Destinations.FORGOT_PASSWORD)
        },

        // Si el login fue exitoso, navegar a la home
        onSuccessLogin = {
            sessionViewModel.checkSession()
            navController.navigate(Destinations.HOME) {
                popUpTo(Destinations.LOGIN) { inclusive = true }
            }
        }
    )
}
