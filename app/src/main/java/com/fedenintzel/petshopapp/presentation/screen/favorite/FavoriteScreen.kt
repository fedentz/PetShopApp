package com.fedenintzel.petshopapp.presentation.screen.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedenintzel.petshopapp.presentation.components.CartItemCard
import com.fedenintzel.petshopapp.presentation.components.SettingsBaseScreen
import com.fedenintzel.petshopapp.presentation.viewmodel.FavoritesViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBack: () -> Unit = {}
) {
    val favorites by viewModel.favorites.collectAsState()

    SettingsBaseScreen(
        title = "Favoritos",
        onBackClick = onBack,
        showBottomButton = false
    ) {
        if (favorites.isEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "No hay productos favoritos",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 64.dp)
            ) {
                items(favorites) { product ->
                    CartItemCard(
                        imageUrl = product.thumbnail,
                        title = product.title,
                        subtitle = product.category,
                        price = product.price
                    )
                }
            }
        }
    }
}
