package com.fedenintzel.petshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.repository.AuthRepository
import com.fedenintzel.petshopapp.presentation.account.CreateAccountScreenContainer
import com.fedenintzel.petshopapp.presentation.account.CreateAccountViewModel
import com.fedenintzel.petshopapp.presentation.screen.cart.CartScreenContent
import com.fedenintzel.petshopapp.presentation.screen.detail.ProductDetailScreen

import com.fedenintzel.petshopapp.presentation.screen.favorites.FavoriteScreen
import com.fedenintzel.petshopapp.presentation.screen.home.HomeScreen
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.FavoritesViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.ProductsViewModel
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private object Destinations {
        const val LOGIN = "login"
        const val CREATE_ACCOUNT = "create_account"
        const val FORGOT_PASSWORD = "forgot_password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // --- 1) Configuramos Retrofit ---
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val authApi = retrofit.create(AuthApiService::class.java)

        // --- 2) Creamos un repositorio compartido ---
        val authRepo = AuthRepository(authApi)

        // --- 3) Hacemos factories para los ViewModels que no inyectaremos con Hilt ---
        val loginFactory = LoginViewModel.Factory(authRepo)
        val createAccountFactory = CreateAccountViewModel.Factory(authRepo)
        // Para ForgotPassword usaremos hiltViewModel(), así que no necesita Factory manual.

        setContent {
            PetShopAppTheme {
                val navController = rememberNavController()

                //CartScreenContent()
               // FavoriteScreen()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(navController = navController)
                    }

                    composable("product_detail/{productId}") { backStackEntry ->
                        val productId =
                            backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                        productId?.let {
                            ProductDetailScreen(
                                navController = navController,
                                productId = it
                            )
                        }
                    }
                }
//                val navController = rememberNavController()
//
//                NavHost(
//                    navController = navController,
//                    startDestination = Destinations.LOGIN
//                ) {
//                    // ─── Pantalla de Login ───
//                    composable(Destinations.LOGIN) {
//                        //-- Instanciamos LoginViewModel con su Factory:
//                        val loginViewModel = ViewModelProvider(
//                            this@MainActivity,
//                            loginFactory
//                        ).get(LoginViewModel::class.java)
//
//                        //-- Convertimos StateFlow<LoginUiState> a State<LoginUiState> indicando un valor inicial:
////                        val loginUiState by loginViewModel.uiState.collectAsState(
////                            initial = LoginUiState()
////                        )
//                        val loginUiState = loginViewModel.uiState
//
//                        //-- Llamamos al Container que contiene la lógica / UI de login:
//                        LoginScreenContainer(
//                            loginViewModel = loginViewModel,
//                            uiState = loginUiState,
//                            onLoginClick = { email, password ->
//                                loginViewModel.login(email, password)
//                            },
//                            onCreateAccountClick = {
//                                navController.navigate(Destinations.CREATE_ACCOUNT)
//                            },
//                            onForgotPasswordClick = {
//                                navController.navigate(Destinations.FORGOT_PASSWORD)
//                            }
//                        )
//                    }
//
//                    // ─── Pantalla de Crear Cuenta ───
//                    composable(Destinations.CREATE_ACCOUNT) {
//                        val createAccountViewModel = ViewModelProvider(
//                            this@MainActivity,
//                            createAccountFactory
//                        ).get(CreateAccountViewModel::class.java)
//
//                        val createAccountUiState by
//                        createAccountViewModel.uiState.collectAsState(
//                            initial = CreateAccountUiState()
//                        )
//
//                        CreateAccountScreenContainer(
//                            createAccountViewModel = createAccountViewModel,
//                            uiState = createAccountUiState,
//                            onCreateAccountClick = { fullName, email, password, agreed ->
//                                createAccountViewModel.createAccount(
//                                    fullName = fullName,
//                                    email = email,
//                                    password = password,
//                                    agreed = agreed
//                                )
//                            },
//                            onLoginClick = {
//                                navController.popBackStack(
//                                    route = Destinations.LOGIN,
//                                    inclusive = false
//                                )
//                            }
//                        )
//                    }
//
//                    // ─── Pantalla de Forgot Password ───
//                    composable(Destinations.FORGOT_PASSWORD) {
//                        // Inyectamos ForgotPasswordViewModel con Hilt
//                        val forgotPasswordViewModel: ForgotPasswordViewModel =
//                            hiltViewModel()
//
//                        val forgotPasswordUiState by
//                        forgotPasswordViewModel.uiState.collectAsState(
//                            initial = ForgotPasswordUiState()
//                        )
//
//                        ForgotPasswordScreenContainer(
//                            viewModel = forgotPasswordViewModel,
//                            uiState = forgotPasswordUiState,
//
//                            // Aquí especificamos “email: String” explícitamente
//                            onSendResetLinkClick = { email: String ->
//                                forgotPasswordViewModel.sendResetLink(email)
//                            },
//
//                            onResetPasswordClick = {
//                                forgotPasswordViewModel.resetPassword()
//                            },
//
//                            onBackToLoginClick = {
//                                navController.popBackStack(
//                                    route = Destinations.LOGIN,
//                                    inclusive = false
//                                )
//                            }
//                        )
//                    }
//                }
            }
        }
    }
}
