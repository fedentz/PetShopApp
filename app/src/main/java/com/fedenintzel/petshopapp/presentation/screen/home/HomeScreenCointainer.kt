package com.fedenintzel.petshopapp.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.presentation.viewModel.HomeViewModel
import androidx.compose.runtime.getValue


@Composable
fun HomeScreenContainer(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val uiState by homeViewModel.uiState.collectAsState()

    HomeScreen(
        navController = navController,
        uiState = uiState,
        onAddToCart = { homeViewModel.addToCart(it) },
        onProductClick = { productId ->
            navController.navigate("product_detail/$productId")
        }
    )
}
