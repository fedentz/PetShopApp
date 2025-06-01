package com.fedenintzel.petshopapp.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.fedenintzel.petshopapp.R

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductDetailViewModel = viewModel()
) {
    val product by viewModel.product.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    var favorite by remember { mutableStateOf(false) }
    var count by remember { mutableStateOf(1) }

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val imageHeightDp = (screenHeightDp * 0.5f).dp // La mitad de la pantalla

    LaunchedEffect(productId) {
        viewModel.fetchProductById(productId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when {
            isLoading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            product == null -> {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(error ?: "Producto no encontrado")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { navController.popBackStack("home", inclusive = false) }) {
                        Text("Volver a la home")
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    // Header
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp, start = 20.dp, end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = Color(0xFFF5F6FA))
                                .clickable { navController.popBackStack() },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                contentDescription = "Back",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "Product Detail",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF222222)
                        )
                        Spacer(Modifier.weight(1f))
                        Box(
                            Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = Color(0xFFF5F6FA))
                                .clickable { favorite = !favorite },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_heart),
                                contentDescription = "Favorite",
                                modifier = Modifier.size(22.dp),
                                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                    if (favorite) Color(0xFF7140FD) else Color(0xFFB3B1B0)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Scrollable Content
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Imagen ocupa la mitad de la pantalla
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(imageHeightDp)
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(25.dp))
                                    .background(Color(0xFFF5F6FA)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(product?.thumbnail),
                                    contentDescription = product?.title,
                                    modifier = Modifier
                                        .fillMaxHeight(0.8f)
                                        .fillMaxWidth(0.8f)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(22.dp))

                        // Product Title
                        Text(
                            text = product?.title ?: "",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF222222),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Product Description
                        Text(
                            text = product?.description ?: "",
                            color = Color(0xFF9A9A9A),
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        // Counter and Price Row
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Counter
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFF5F6FA))
                                        .clickable { if (count > 1) count-- },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_minus),
                                        contentDescription = "Menos",
                                        modifier = Modifier.size(18.dp),
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF222222))
                                    )
                                }
                                Text(
                                    text = "$count",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .width(36.dp)
                                        .padding(horizontal = 4.dp),
                                    color = Color(0xFF222222),
                                    textAlign = TextAlign.Center
                                )
                                Box(
                                    Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFF5F6FA))
                                        .clickable { count++ },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_plus),
                                        contentDescription = "Más",
                                        modifier = Modifier.size(18.dp),
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF222222))
                                    )
                                }
                            }
                            // Price
                            Text(
                                text = "$${String.format("%.2f", (product?.price ?: 0.0) * count)}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                color = Color(0xFF222222)
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))
                    }

                    // Button at the bottom
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 26.dp, start = 18.dp, end = 18.dp)
                    ) {
                        Button(
                            onClick = { /* agregar lógica para el carrito */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(22.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7140FD)),
                            contentPadding = PaddingValues()
                        ) {
                            Text(
                                text = "Add to Cart",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}