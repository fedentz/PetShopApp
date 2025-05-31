package com.fedenintzel.petshopapp.presentation.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedenintzel.petshopapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.geometry.Offset
import com.fedenintzel.petshopapp.presentation.components.ProductCard
import androidx.compose.ui.zIndex

@JvmOverloads
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel() // HomeViewModel debe tener uiState con products, isLoading, error y addToCart
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

        // Banner
        item {
            BannerComposable(
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

@Composable
fun BannerComposable(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(170.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(36.dp))
                .background(Color(0xFF7140FD))
                .align(Alignment.Center)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color(0xFF9F7CFF).copy(alpha = 0.29f),
                    radius = size.minDimension * 0.22f,
                    center = androidx.compose.ui.geometry.Offset(x = size.width * 0.18f, y = size.height * 0.23f)
                )
                drawCircle(
                    color = Color(0xFF9F7CFF).copy(alpha = 0.29f),
                    radius = size.minDimension * 0.19f,
                    center = androidx.compose.ui.geometry.Offset(x = size.width * 0.90f, y = size.height * 0.18f)
                )
                drawCircle(
                    color = Color(0xFF9F7CFF).copy(alpha = 0.29f),
                    radius = size.minDimension * 0.16f,
                    center = androidx.compose.ui.geometry.Offset(x = size.width * 0.80f, y = size.height * 0.82f)
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 18.dp)
                .fillMaxHeight()
                .width(145.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.comidabanner),
                contentDescription = "Royal Canin Back",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = 8.dp, y = 12.dp)
                    .zIndex(1f)
            )

            // Imagen de adelante (más grande, adelante)
            Image(
                painter = painterResource(R.drawable.comidabanner),
                contentDescription = "Royal Canin Front",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
                    .offset(x = 28.dp, y = 0.dp)
                    .zIndex(2f)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(start = 170.dp, end = 30.dp, top = 32.dp, bottom = 24.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Royal Canin\nAdult Pomeraniann",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )
            Text(
                "Get an interesting promo\nhere, without conditions",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp
            )
        }
    }
}

