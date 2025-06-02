package com.fedenintzel.petshopapp.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.presentation.account.CreateAccountScreenContainer
import com.fedenintzel.petshopapp.presentation.account.CreateAccountViewModel
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordScreenContainer
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordViewModel
import com.fedenintzel.petshopapp.presentation.login.LoginScreenContainer
import com.fedenintzel.petshopapp.presentation.screen.OnBoardingScreen
import com.fedenintzel.petshopapp.presentation.screen.home.HomeScreenContainer
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsPageScreen
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.navigation.settings.SettingsNavGraph
import com.fedenintzel.petshopapp.presentation.screen.cart.CartScreenContent


@Composable
fun NavigationWrapper(
    loginFactory: ViewModelProvider.Factory,
    createAccountFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.SETTINGS
    ) {
        // Onboarding
        composable(Destinations.ONBOARDING) {
            OnBoardingScreen(
                onContinue = {
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        // Login
        composable(Destinations.LOGIN) {
            val loginViewModel = ViewModelProvider(
                navController.context as ComponentActivity,
                loginFactory
            )[LoginViewModel::class.java]

            LoginScreenContainer(
                loginViewModel = loginViewModel,
                uiState = loginViewModel.uiState,
                onLoginClick = { email, password ->
                    loginViewModel.login(email, password)
                    navController.navigate(Destinations.HOME) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                    }
                },
                onCreateAccountClick = {
                    navController.navigate(Destinations.CREATE_ACCOUNT)
                },
                onForgotPasswordClick = {
                    navController.navigate(Destinations.FORGOT_PASSWORD)
                }
            )
        }

        // Crear cuenta
        composable(Destinations.CREATE_ACCOUNT) {
            val viewModel = ViewModelProvider(
                navController.context as ComponentActivity,
                createAccountFactory
            )[CreateAccountViewModel::class.java]

            val uiState = viewModel.uiState.collectAsState(initial = CreateAccountUiState()).value

            CreateAccountScreenContainer(
                createAccountViewModel = viewModel,
                uiState = uiState,
                onCreateAccountClick = { fullName, email, password, agreed ->
                    viewModel.createAccount(fullName, email, password, agreed)
                },
                onLoginClick = {
                    navController.popBackStack(Destinations.LOGIN, false)
                }
            )
        }

        // Forgot Password
        composable(Destinations.FORGOT_PASSWORD) {
            val viewModel: ForgotPasswordViewModel = hiltViewModel()

            val uiState = viewModel.uiState.collectAsState(initial = ForgotPasswordUiState()).value

            ForgotPasswordScreenContainer(
                viewModel = viewModel,
                uiState = uiState,
                onSendResetLinkClick = viewModel::sendResetLink,
                onResetPasswordClick = viewModel::resetPassword,
                onBackToLoginClick = {
                    navController.popBackStack(Destinations.LOGIN, false)
                }
            )
        }

        // Home
        composable(Destinations.HOME) {
            HomeScreenContainer(navController = navController)
        }

        // Settings
        composable(Destinations.SETTINGS) {
            SettingsNavGraph(
                onBackClick = { navController.popBackStack() }
            )
        }

    }
}
