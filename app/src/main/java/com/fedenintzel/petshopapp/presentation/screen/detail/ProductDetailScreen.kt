package com.fedenintzel.petshopapp.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.viewmodel.CartViewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.ProductDetailViewModel
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    cartViewModel: CartViewModel
) {
    val product by viewModel.product.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val showSnackbar by cartViewModel.showSnackbar

    val snackbarHostState = remember { SnackbarHostState() }
    var count by remember { mutableStateOf(1) }

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val imageHeightDp = (screenHeightDp * 0.5f).dp

    LaunchedEffect(productId) {
        viewModel.fetchProductById(productId)
    }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("Product added to Cart")
            cartViewModel.clearSnackbar()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
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
                    Text(error ?: "Product not found")
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = { navController.popBackStack("home", inclusive = false) }) {
                        Text("Back to Home")
                    }
                }
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(paddingValues)

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                       // Encabezado: Flecha, título, y favorito
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
//                                .padding(
//                                    WindowInsets.safeDrawing.only(WindowInsetsSides.Top).asPaddingValues()
//                                )
                                .padding(top = 14.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .size(46.dp)
                                    .shadow(
                                        elevation = 10.dp,
                                        shape = RoundedCornerShape(16.dp),
                                        clip = false,
                                        ambientColor = Color(0x33000000),
                                        spotColor = Color(0x33000000)
                                    )
                                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                                    .clip(RoundedCornerShape(16.dp))
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_left),
                                    contentDescription = "Back"
                                )
                            }

                            Text(
                                text = "Product Detail",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            IconButton(
                                onClick = { viewModel.toggleFavorite() },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color = Color(0xFFF5F6FA), shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (isFavorite) Color.Red else Color(0xFFB3B1B0)
                                )
                            }
                        }




                        Spacer(modifier = Modifier.height(12.dp))

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
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
                                        painter = rememberAsyncImagePainter(product!!.thumbnail),
                                        contentDescription = product!!.title,
                                        modifier = Modifier
                                            .fillMaxHeight(0.8f)
                                            .fillMaxWidth(0.8f)
                                            .clip(RoundedCornerShape(12.dp)),
                                        contentScale = androidx.compose.ui.layout.ContentScale.Fit
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(22.dp))

                            Text(
                                text = product!!.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFF222222),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = product!!.description,
                                color = Color(0xFF9A9A9A),
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
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
                                Text(
                                    text = "$${String.format("%.2f", product!!.price * count)}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color(0xFF222222)
                                )
                            }

                            Spacer(modifier = Modifier.height(18.dp))
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 26.dp, start = 18.dp, end = 18.dp)
                        ) {
                            Button(
                                onClick = {
                                    repeat(count) {
                                        cartViewModel.addToCart(product!!)
                                    }
                                },
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
}
