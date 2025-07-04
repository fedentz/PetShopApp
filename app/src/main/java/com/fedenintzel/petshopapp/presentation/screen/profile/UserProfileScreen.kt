package com.fedenintzel.petshopapp.presentation.screen.profile

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SELLER_PROFILE
import com.fedenintzel.petshopapp.presentation.components.*
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.presentation.viewmodel.FavoritesViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.ProductsViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.SessionViewModel


/**
 * Pantalla de perfil de usuario final
 *
 * Muestra:
 * - Control de modo (Profile / Seller Mode)
 * - Header del usuario con avatar y nombre
 * - Filtros secundarios (Saved / Edit Profile)
 * - Grilla de productos favoritos
 *
 * Usa Scaffold y está preparada para recibir bottomBar desde el nivel superior.
 *
 * Recibe los productos favoritos desde el viewmodel
 */
@Composable
fun UserProfileScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    productsViewModel: ProductsViewModel = hiltViewModel(),
    viewModel: SessionViewModel = hiltViewModel()

) {
    val favoriteProducts by favoritesViewModel.favorites.collectAsState()
    val user = viewModel.user.value
    LaunchedEffect(user) {
        Log.d("UserProfile", "Usuario actual: $user")
    }



   Scaffold(
        bottomBar = {
            BottomBar(
                selected = BottomBarItem.PROFILE,
                onHomeClick = { navController.navigate(Destinations.HOME) },
                onTimeClick = { /* a futuro */ },
                onBagClick = { navController.navigate(Destinations.CART) },
                onProfileClick = { /* ya estamos en Profile */ },
                modifier = Modifier
                    .height(64.dp)
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selectedTab by remember { mutableStateOf(0) }

            SegmentedControl(
                options = listOf("Profile", "Seller Mode"),
                selectedIndex = selectedTab,
                onOptionSelected = {
                    selectedTab = it
                    if (it == 1) {
                        navController.navigate(SELLER_PROFILE)
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            ProfileHeader(
                backgroundPainter = painterResource(id = R.drawable.profile_background),
                profileImage = painterResource(id = R.drawable.profile_avatar),
                userName = user?.firstName ?:"User"
            )

            Spacer(modifier = Modifier.height(16.dp))

            var filterIndex by remember { mutableStateOf(0) }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HorizontalFilterButtons(
                    options = listOf(
                        "Saved" to null,
                        "Edit Profile" to { navController.navigate(Destinations.SETTINGS_ACCOUNT_SCREEN) }
                    ),
                    selectedIndex = filterIndex,
                    onSelect = { filterIndex = it },
                    modifier = Modifier
                        .wrapContentWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (favoriteProducts.isEmpty()) {
                    Text(text = "No favorite products added yet")
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(favoriteProducts) { product ->
                            ProductCard(
                                name = product.title,
                                price = product.price,
                                imageUrl = product.thumbnail,
                                onAddClick = {  },
                                onCardClick = { navController.navigate("product_detail/${product.id}") }
                            )
                        }
                    }
                }
            }

        }
    }
}




