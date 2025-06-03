package com.fedenintzel.petshopapp.presentation.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SELLER_PROFILE
import com.fedenintzel.petshopapp.presentation.components.*
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme

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
 */
@Composable
fun UserProfileScreen(navController: NavController) {
    val favoriteProducts = listOf(
        Product("RC Kitten", 20.99, R.drawable.product_image),
        Product("RC Persian", 22.99, R.drawable.product_image),
        Product("RC Kitten", 20.99, R.drawable.product_image),
        Product("RC Persian", 22.99, R.drawable.product_image),
        Product("RC Kitten", 20.99, R.drawable.product_image),
        Product("RC Persian", 22.99, R.drawable.product_image),
        Product("RC Kitten", 20.99, R.drawable.product_image),
        Product("RC Persian", 22.99, R.drawable.product_image)
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // Control superior Profile y Seller Mode
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
                userName = "Abduldul"
            )

            Spacer(modifier = Modifier.height(16.dp))

            var filterIndex by remember { mutableStateOf(0) }
            HorizontalFilterButtons(
                options = listOf("Saved", "Edit Profile"),
                selectedIndex = filterIndex,
                onSelect = { filterIndex = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Solo la grilla de productos es scrolleable
            Box(modifier = Modifier.weight(1f)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(favoriteProducts) { product ->
                        ProductCardCart(
                            name = product.name,
                            price = product.price,
                            imageResId = product.imageResId,
                            onAddClick = { /*  */ },
                            onCardClick = { /*  */ }
                        )
                    }
                }
            }
        }
    }
}


data class Product(
    val name: String,
    val price: Double,
    val imageResId: Int
)


