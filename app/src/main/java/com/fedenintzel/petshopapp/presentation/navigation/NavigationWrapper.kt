package com.fedenintzel.petshopapp.presentation.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.presentation.account.CreateAccountScreenContainer
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordScreenContainer
import com.fedenintzel.petshopapp.presentation.components.DrawerContent
import com.fedenintzel.petshopapp.presentation.login.LoginScreenContainer
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.ADD_NEW_PAYMENT_METHOD
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SETTINGS
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SETTINGS_ACCOUNT_SCREEN
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SETTINGS_FAQ_SCREEN
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SETTINGS_NOTIFICATIONS_SCREEN
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SETTINGS_PRIVACY_SCREEN
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SETTINGS_SECURITY_SCREEN
import com.fedenintzel.petshopapp.presentation.screen.OnBoardingScreen
import com.fedenintzel.petshopapp.presentation.screen.bestseller.BestSellerScreen
import com.fedenintzel.petshopapp.presentation.screen.cart.CartScreenContent
import com.fedenintzel.petshopapp.presentation.screen.detail.ProductDetailScreen
import com.fedenintzel.petshopapp.presentation.screen.home.HomeScreen
import com.fedenintzel.petshopapp.presentation.screen.notifications.NotificationsScreen
import com.fedenintzel.petshopapp.presentation.screen.payment.AddNewPaymentScreen
import com.fedenintzel.petshopapp.presentation.screen.payment.ChoosePaymentMethodScreen
import com.fedenintzel.petshopapp.presentation.screen.payment.PaymentSuccessScreen
import com.fedenintzel.petshopapp.presentation.screen.profile.SellerProfileScreen
import com.fedenintzel.petshopapp.presentation.screen.profile.UserProfileScreen
import com.fedenintzel.petshopapp.presentation.screen.search.SearchScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsAccountScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsChangeEmailScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsChangePasswordScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsFaqScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsNotificationsScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsPageScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsPrivacyScreen
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsSecurityScreen
import com.fedenintzel.petshopapp.presentation.viewmodel.SessionViewModel
import kotlinx.coroutines.launch


@Composable
fun NavigationWrapper(
    sessionViewModel: SessionViewModel = hiltViewModel()
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
                        navController.navigate(SETTINGS)
                    }
                },
                navController = navController,
                sessionViewModel = sessionViewModel
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
                    navController = navController,
                    sessionViewModel = sessionViewModel,
                    onContinue = {
                        navController.navigate(Destinations.LOGIN) {
                            popUpTo(Destinations.ONBOARDING) { inclusive = true }
                        }
                    }
                )
            }


            // Login
            composable(Destinations.LOGIN) {

                LoginScreenContainer(

                    navController = navController,
                    sessionViewModel = sessionViewModel
                )
            }


            // Crear cuenta
            composable(Destinations.CREATE_ACCOUNT) {

                CreateAccountScreenContainer(
                   onLoginClick = {
                        navController.popBackStack(Destinations.LOGIN, false)
                    },
                    navController = navController,
                    sessionViewModel = sessionViewModel
                )
            }


            // Forgot Password
            composable(Destinations.FORGOT_PASSWORD) {


                ForgotPasswordScreenContainer(

                    onBackToLoginClick = {
                        navController.popBackStack(Destinations.LOGIN, false)
                    }
                )
            }


            // Home
            composable(HomeDestinations.HOME) {
                HomeScreen(
                    navController = navController,
                    sessionViewModel = sessionViewModel)
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
            composable(SETTINGS) {
                SettingsPageScreen(
                    categories = FakeSettingsDataProvider.getSettingsCategories(),
                    onBackClick = { navController.popBackStack() },
                    onItemClick = { item ->
                        val route = when (item.title) {
                            "Account" -> SETTINGS_ACCOUNT_SCREEN
                            "Notification" -> SETTINGS_NOTIFICATIONS_SCREEN
                            "Privacy" -> SETTINGS_PRIVACY_SCREEN
                            "Security" -> SETTINGS_SECURITY_SCREEN
                            "FAQ" -> SETTINGS_FAQ_SCREEN
                            "Payment Method" -> ADD_NEW_PAYMENT_METHOD
                            else -> null
                        }

                        route?.let { navController.navigate(it) }
                    },
                    navController = navController,
                    sessionViewModel = sessionViewModel)
            }

            // Cart
            composable(Destinations.CART) {
                 CartScreenContent(navController = navController)
            }

            //User Profile
            composable(Destinations.USER_PROFILE) {
                UserProfileScreen(
                    navController = navController,
                    sessionViewModel = sessionViewModel
                )
            }

            //Seller Profile
            composable(Destinations.SELLER_PROFILE) {
                SellerProfileScreen(navController = navController)
            }

            // Payment Method
            composable (Destinations.CHOOSE_PAYMENT_METHOD) {
                ChoosePaymentMethodScreen(navController = navController)
            }

            composable (ADD_NEW_PAYMENT_METHOD) {
                AddNewPaymentScreen(navController = navController)
            }

            composable (Destinations.PAYMENT_SUCCESS) {
                PaymentSuccessScreen(navController = navController)
            }

            //Settings Screens

            composable(SETTINGS_ACCOUNT_SCREEN) {
                SettingsAccountScreen(
                    onBackClick = { navController.popBackStack() },
                    onSaveChanges = { name, username, email ->
                        // lógica para guardar cambios
                    }
                )
            }


            composable(SETTINGS_NOTIFICATIONS_SCREEN) {
                SettingsNotificationsScreen(
                    onBackClick = { navController.popBackStack() },
                    categories = FakeSettingsDataProvider.getSettingsNotifications(),
                    onItemClick = {}
                )
            }


            composable(SETTINGS_PRIVACY_SCREEN) {
                SettingsPrivacyScreen(
                    onBackClick = { navController.popBackStack() },
                    categories = FakeSettingsDataProvider.getSettingsPrivacy(),
                    onItemClick = {}
                )
            }


            composable(SETTINGS_SECURITY_SCREEN) {
                SettingsSecurityScreen(
                    navController = navController,
                    onBackClick = { navController.popBackStack() },
                    onItemClick = { item ->
                        when (item.title) {
                            "Change Password" -> navController.navigate(Destinations.CHANGE_PASSWORD)
                            "Change Email" -> navController.navigate(Destinations.CHANGE_EMAIL)
                            else -> {}
                        }
                    }
                )
            }

            composable(SETTINGS_FAQ_SCREEN) {
                SettingsFaqScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Destinations.CHANGE_EMAIL) {
                SettingsChangeEmailScreen(
                    onBackClick = { navController.popBackStack() },
                    onSaveEmail = { email ->
                        // Acá podés validar el email o usar un ViewModel si querés
                        navController.popBackStack() // Volver luego de guardar
                    }
                )
            }

            composable(Destinations.CHANGE_PASSWORD) {
                SettingsChangePasswordScreen(
                    onBackClick = { navController.popBackStack() },
                    onChangePassword = { newPassword, confirmPassword ->
                        if (newPassword == confirmPassword) {
                            // Guardar la nueva contraseña o usar ViewModel
                            navController.popBackStack()
                        } else {
                            // Mostrar error si querés (snackbar, toast, etc.)
                        }
                    }
                )
            }

        }
    }

}