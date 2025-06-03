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
import com.fedenintzel.petshopapp.navigation.NavigationWrapper
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

import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            setContent {
                PetShopAppTheme {
                    NavigationWrapper(
                        
                    )
                }

            }
        }


    }
}
