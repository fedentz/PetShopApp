package com.fedenintzel.petshopapp.navigation


import androidx.activity.ComponentActivity
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fedenintzel.petshopapp.navigation.settings.SettingsNavGraph
import com.fedenintzel.petshopapp.presentation.account.CreateAccountScreenContainer
import com.fedenintzel.petshopapp.presentation.account.CreateAccountViewModel
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordScreenContainer
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordViewModel
import com.fedenintzel.petshopapp.presentation.components.DrawerContent
import com.fedenintzel.petshopapp.presentation.login.LoginScreenContainer
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.presentation.screen.OnBoardingScreen
import com.fedenintzel.petshopapp.presentation.screen.bestseller.BestSellerScreen
import com.fedenintzel.petshopapp.presentation.screen.cart.CartScreenContent
import com.fedenintzel.petshopapp.presentation.screen.detail.ProductDetailScreen
import com.fedenintzel.petshopapp.presentation.screen.home.HomeScreen
import com.fedenintzel.petshopapp.presentation.screen.notifications.NotificationsScreen
import com.fedenintzel.petshopapp.presentation.screen.profile.SellerProfileScreen
import com.fedenintzel.petshopapp.presentation.screen.profile.UserProfileScreen
import com.fedenintzel.petshopapp.presentation.screen.search.SearchScreen
import kotlinx.coroutines.launch


@Composable
fun NavigationWrapper(
    loginFactory: ViewModelProvider.Factory,
    createAccountFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onSettingsClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(Destinations.SETTINGS)
                    }
                }
            )
        }
    )

    {
        NavHost(
            navController = navController,
            startDestination = Destinations.ONBOARDING
        ) {
            // Onboarding
            composable(Destinations.ONBOARDING) {
                OnBoardingScreen(
                    onContinue = {
                        navController.navigate(Destinations.HOME) {
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


                val uiState =
                    viewModel.uiState.collectAsState(initial = CreateAccountUiState()).value


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


                val uiState =
                    viewModel.uiState.collectAsState(initial = ForgotPasswordUiState()).value


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
            composable(HomeDestinations.HOME) {
                HomeScreen(navController = navController)
            }
            composable(HomeDestinations.NOTIFICATIONS) {
                NotificationsScreen(onBack = { navController.popBackStack() })
            }
            composable(HomeDestinations.SEARCH) {
                SearchScreen(navController = navController)
            }
            composable(HomeDestinations.BEST_SELLER) {
                BestSellerScreen(navController = navController)
            }
            composable(
                route = "${HomeDestinations.PRODUCT_DETAIL}/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                ProductDetailScreen(
                    navController = navController,
                    productId = productId
                )
            }


            // Settings
            composable(Destinations.SETTINGS) {
                SettingsNavGraph(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Cart
            composable(Destinations.CART) {
                 CartScreenContent()
            }

            //User Profile
            composable(Destinations.USER_PROFILE) {
                UserProfileScreen(navController = navController)
            }

            //Seller Profile
            composable(Destinations.SELLER_PROFILE) {
                SellerProfileScreen(navController = navController)
            }


        }
    }

}