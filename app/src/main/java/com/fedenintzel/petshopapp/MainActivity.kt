package com.fedenintzel.petshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.remote.ProductsApi
import com.fedenintzel.petshopapp.data.repository.AuthRepository
import com.fedenintzel.petshopapp.data.repository.ProductsRepository
import com.fedenintzel.petshopapp.navigation.NavigationWrapper
import com.fedenintzel.petshopapp.presentation.account.CreateAccountScreenContainer
import com.fedenintzel.petshopapp.presentation.account.CreateAccountViewModel
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordScreenContainer
import com.fedenintzel.petshopapp.presentation.account.ForgotPasswordViewModel
import com.fedenintzel.petshopapp.presentation.login.LoginScreenContainer
import com.fedenintzel.petshopapp.presentation.screen.OnBoardingScreen
import com.fedenintzel.petshopapp.presentation.screen.home.HomeScreenContainer
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordUiState
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private object Destinations {

        // profile
        const val ONBOARDING = "onboarding"
        const val LOGIN = "login"
        const val CREATE_ACCOUNT = "create_account"
        const val FORGOT_PASSWORD = "forgot_password"

        //home
        const val HOME = "home"

        // cart
        const val CART = "cart"

        // payment
        const val ADD_NEW_PAYMENT = "add_new_payment"

        // settings
        const val SETTINGS = "settings"
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
        val productsApi = retrofit.create(ProductsApi::class.java)


        // --- 2) Creamos un repositorio compartido ---
        val authRepo = AuthRepository(authApi)
        //val productsRepository = ProductsRepository(productsApi)


        // --- 3) Hacemos factories para los ViewModels que no inyectaremos con Hilt ---
        val loginFactory = LoginViewModel.Factory(authRepo)
        val createAccountFactory = CreateAccountViewModel.Factory(authRepo)
        //val homeViewModelFactory = HomeViewModel.Factory(productsRepository)



        // Para ForgotPassword usaremos hiltViewModel(), así que no necesita Factory manual.

        setContent {
            setContent {
                PetShopAppTheme {
                    NavigationWrapper(
                        loginFactory = loginFactory,
                        createAccountFactory = createAccountFactory
                    )
                }
            }
        }

    }
}
