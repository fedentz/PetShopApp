package com.fedenintzel.petshopapp.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.fedenintzel.petshopapp.presentation.components.ProductCard
import com.fedenintzel.petshopapp.presentation.components.Banner
import com.fedenintzel.petshopapp.R
import androidx.compose.ui.draw.clip

@JvmOverloads
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var selectedCategory by remember { mutableStateOf("Food") }
    val categories = listOf("Food", "Toys", "Accesories")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Location",
                        fontSize = 12.sp,
                        color = Color(0xFFB3B1B0)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Jebres, Surakarta",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_down),
                            contentDescription = "Expand location",
                            tint = Color(0xFFB3B1B0),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Row {
                    IconButton(onClick = { /* search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* notifications action */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                }
            }
        }

        // Banner --> USÁ EL COMPONENTE
        item {
            Banner(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Categorías
        item {
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
                    Text(
                        "View All",
                        color = Color(0xFF7140FD)
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                items(categories) { category ->
                    val selected = category == selectedCategory
                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (selected) Color(0xFF7140FD) else Color(0xFFF8F8F8))
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 22.dp, vertical = 10.dp)
                    ) {
                        Text(
                            category,
                            color = if (selected) Color.White else Color(0xFFB3B1B0)
                        )
                    }
                }
            }
        }

        // Best Seller header
        item {
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
                TextButton(onClick = { /* Ver todas */ }) {
                    Text(
                        "View All",
                        color = Color(0xFF7140FD)
                    )
                }
            }
        }

        // Productos
        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else if (uiState.error != null) {
            item {
                Text(
                    text = uiState.error ?: "Error",
                    color = Color.Red,
                    modifier = Modifier.padding(32.dp)
                )
            }
        } else {
            items(uiState.products.chunked(2)) { rowProducts ->
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
                            onAddClick = {
                                viewModel.addToCart(product)
                            },
                            onCardClick = { /* TODO: Navegar a detalle */ },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Si la fila tiene solo un producto, agregamos un Spacer para el grid
                    if (rowProducts.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}