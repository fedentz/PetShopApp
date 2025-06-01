package com.fedenintzel.petshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.repository.AuthRepository
import com.fedenintzel.petshopapp.presentation.login.LoginScreen
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginViewModel
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1) Configurar Retrofit y AuthApiService
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val authApi = retrofit.create(AuthApiService::class.java)

        // 2) Crear AuthRepository
        val authRepo = AuthRepository(authApi)

        // 3) Configurar ViewModel con Factory
        val viewModelFactory = LoginViewModel.Factory(authRepo)
        val loginViewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        setContent {
            PetShopAppTheme {
                // 4) Obtener estado de UI
                val uiState = remember { loginViewModel }.uiState

                // 5) Mostrar LoginScreen
                LoginScreen(
                    uiState = uiState,
                    onLoginClick = { email, password ->
                        loginViewModel.login(email, password)
                    },
                    onCreateAccountClick = {
                        // TODO: Navegar a pantallas de registro si aplica
                    }
                )
            }
        }
    }
}
