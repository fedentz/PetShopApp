package com.fedenintzel.petshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.repository.AuthRepository
import com.fedenintzel.petshopapp.presentation.account.CreateAccountScreenContainer
import com.fedenintzel.petshopapp.presentation.account.CreateAccountViewModel
import com.fedenintzel.petshopapp.presentation.login.LoginScreenContainer
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private object Destinations {
        const val LOGIN = "login"
        const val CREATE_ACCOUNT = "create_account"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1) Configuramos Retrofit una sola vez
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val authApi = retrofit.create(AuthApiService::class.java)

        // 2) Creamos un repositorio compartido
        val authRepo = AuthRepository(authApi)

        // 3) Factories para ViewModels
        val loginFactory = LoginViewModel.Factory(authRepo)
        val createAccountFactory = CreateAccountViewModel.Factory(authRepo)

        // 4) Instanciamos ambos ViewModels
        val loginViewModel = ViewModelProvider(this, loginFactory)
            .get(LoginViewModel::class.java)
        val createAccountViewModel = ViewModelProvider(this, createAccountFactory)
            .get(CreateAccountViewModel::class.java)

        setContent {
            PetShopAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Destinations.LOGIN
                ) {
                    // ─── Pantalla de Login ───
                    composable(Destinations.LOGIN) {
                        LoginScreenContainer(
                            loginViewModel = loginViewModel,
                            onLoginClick = { email, password ->
                                loginViewModel.login(email, password)
                            },
                            onCreateAccountClick = {
                                navController.navigate(Destinations.CREATE_ACCOUNT)
                            }
                        )
                    }

                    // ─── Pantalla de Crear Cuenta ───
                    composable(Destinations.CREATE_ACCOUNT) {
                        CreateAccountScreenContainer(
                            createAccountViewModel = createAccountViewModel,
                            onCreateAccountClick = { fullName, email, password, agreed ->
                                createAccountViewModel.createAccount(
                                    fullName = fullName,
                                    email = email,
                                    password = password,
                                    agreed = agreed
                                )
                            },
                            onLoginClick = {
                                navController.popBackStack(
                                    route = Destinations.LOGIN,
                                    inclusive = false
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
