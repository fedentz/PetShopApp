package com.fedenintzel.petshopapp.presentation.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.presentation.components.CartItemCard
import com.fedenintzel.petshopapp.presentation.viewmodel.CartViewModel
import com.fedenintzel.petshopapp.ui.theme.Poppins
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CartScreenContent(
    viewModel: CartViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Text(
                    text = state.error,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.cart != null -> {
                val cart = state.cart

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 245.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    items(cart.products) { product ->
                        CartItemCard(
                            imageUrl = product.thumbnail,
                            title = product.title,
                            subtitle = "Qty: ${product.quantity}",
                            price = product.price
                        )
                    }
                }

                // Panel inferior fijo
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(245.dp),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = Color.White,
                    shadowElevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 24.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "${cart.totalProducts} Items",
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFFB3B1B0)
                                )
                                Text(
                                    "$${"%.2f".format(cart.total)}",
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFFB3B1B0)
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Tax",
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFFB3B1B0)
                                )
                                Text(
                                    "$1.99",
                                    fontSize = 12.sp,
                                    fontFamily = Poppins,
                                    color = Color(0xFFB3B1B0)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Totals",
                                    fontSize = 16.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    "$${"%.2f".format(cart.discountedTotal)}",
                                    fontSize = 24.sp,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Button(
                            onClick = { /* Acción de checkout */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7140FD)),
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                        ) {
                            Text("Checkout", fontFamily = Poppins, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

