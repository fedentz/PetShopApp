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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.presentation.navigation.Destinations.SELLER_PROFILE
import com.fedenintzel.petshopapp.presentation.components.*
import com.fedenintzel.petshopapp.presentation.viewmodel.ProductsViewModel
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme

/**
 * Pantalla de perfil para el modo vendedor.
 *
 * Muestra:
 * - Control de modo (Profile / Seller Mode)
 * - Header de la tienda
 * - Estadísticas (Followers, Following, Sales)
 * - Tabs: Product / Sold / Stats
 * - Grilla de productos publicados
 */
@Composable
fun SellerProfileScreen(
    navController: NavController,
    productsViewModel: ProductsViewModel = hiltViewModel() ) {

    val productState by productsViewModel.state.collectAsState()

    val sellerProducts = listOf(
        ProductSeller("RC Kitten", 20.99, R.drawable.product_image),
        ProductSeller("RC Persian", 22.99, R.drawable.product_image),
        ProductSeller("RC Kitten", 20.99, R.drawable.product_image),
        ProductSeller("RC Persian", 22.99, R.drawable.product_image)
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Selector superior Profile o Seller Mode
            var selectedTab by remember { mutableStateOf(1) } // ← Arranca en Seller Mode

            SegmentedControl(
                options = listOf("Profile", "Seller Mode"),
                selectedIndex = selectedTab,
                onOptionSelected = {
                    selectedTab = it
                    if (it == 0) {
                        navController.navigate(Destinations.USER_PROFILE) {
                            popUpTo(Destinations.SELLER_PROFILE) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Header de la tienda
            ProfileHeader(
                backgroundPainter = painterResource(id = R.drawable.seller_background),
                profileImage = painterResource(id = R.drawable.seller_avatar),
                userName = "Pittashop"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Stats del vendedor
            StatsRow(
                followers = 109,
                following = 992,
                sales = 80,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tabs Product / Sold / Stats
            var sectionIndex by remember { mutableStateOf(0) }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HorizontalFilterButtons(
                    options = listOf(
                        "Product" to null,
                        "Sold" to null,
                        "Stats" to null
                    ),
                    selectedIndex = sectionIndex,
                    onSelect = { sectionIndex = it },
                    modifier = Modifier.wrapContentWidth()
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            // Grilla de productos publicados (solo si está en Product)
            if (sectionIndex == 0) {
                Box(modifier = Modifier.weight(1f)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(productState.products) { product ->
                            ProductCard(
                                name = product.title,
                                price = product.price,
                                imageUrl = product.thumbnail,
                                onAddClick = { /* No debería ir a ningún carrito */ },
                                onCardClick = { navController.navigate("product_detail/${product.id}") }
                            )
                        }
                    }
                }
            } else {
                //  agregar contenido para Sold / Stats
                Box(modifier = Modifier.weight(1f)) {
                    // Placeholder para futuras secciones
                }
            }
        }
    }
}

data class ProductSeller(
    val name: String,
    val price: Double,
    val imageResId: Int
)