package com.fedenintzel.petshopapp.presentation.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.presentation.components.*
import com.fedenintzel.petshopapp.presentation.screen.location.LocationSheet
import com.fedenintzel.petshopapp.presentation.viewmodel.CartViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.ProductsViewModel
import com.google.firebase.auth.FirebaseAuth

@JvmOverloads
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ProductsViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val user = FirebaseAuth.getInstance().currentUser
    Log.d("Home", "Usuario actual: ${user}")

    val state by viewModel.state.collectAsState()
    val showSnackbar by cartViewModel.showSnackbar
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("Product added to cart")
            cartViewModel.clearSnackbar()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        val categories = listOf("Food", "Toys", "Accesories")
        var selectedCategory by remember { mutableStateOf(categories.first()) }
        var showLocationSheet by remember { mutableStateOf(false) }

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp)
            ) {
                Header(
                    onLocationClick = { showLocationSheet = true },
                    onNotificationsClick = { navController.navigate("notifications") },
                    onSearchClick = { navController.navigate("search") }
                )
                LocationSheet(
                    show = showLocationSheet,
                    onDismiss = { showLocationSheet = false }
                )

                Banner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Category",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    TextButton(onClick = { /* Ver todas */ }) {
                        Text("View All", color = Color(0xFF7140FD))
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                CategorySelector(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Best Seller",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    TextButton(onClick = { navController.navigate("best_seller") }) {
                        Text("View All", color = Color(0xFF7140FD))
                    }
                }

                if (state.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (state.error != null) {
                    Text(
                        text = state.error ?: "Error",
                        color = Color.Red,
                        modifier = Modifier.padding(32.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 96.dp)
                    ) {
                        items(state.products.chunked(2)) { rowProducts ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 18.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                rowProducts.forEach { product ->
                                    ProductCard(
                                        name = product.title,
                                        price = product.price,
                                        imageUrl = product.thumbnail,
                                        onAddClick = { cartViewModel.addToCart(product) },
                                        onCardClick = {
                                            navController.navigate("product_detail/${product.id}")
                                        },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                if (rowProducts.size < 2) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            BottomBar(
                selected = BottomBarItem.HOME,
                onHomeClick = { /* ya estás en Home */ },
                onTimeClick = { /* a futuro */ },
                onBagClick = { navController.navigate(Destinations.CART) },
                onProfileClick = { navController.navigate(Destinations.USER_PROFILE) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(64.dp)
            )
        }
    }
}