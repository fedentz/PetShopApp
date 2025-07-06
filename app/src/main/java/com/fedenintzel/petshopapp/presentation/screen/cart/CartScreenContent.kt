package com.fedenintzel.petshopapp.presentation.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.presentation.components.CartItemCard
import com.fedenintzel.petshopapp.presentation.viewmodel.CartViewModel
import com.fedenintzel.petshopapp.ui.theme.Poppins
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.navigation.Destinations

@Composable
fun CartScreenContent(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var itemToRemove by remember { mutableStateOf<Int?>(null) }

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

                Column(modifier = Modifier.fillMaxSize()) {
                    //  Top bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                WindowInsets.safeDrawing.only(WindowInsetsSides.Top).asPaddingValues()
                            )
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        contentAlignment = Alignment.Center
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
                                .align(Alignment.CenterStart)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = "Back"
                            )
                        }

                        Text(
                            text = "Cart",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }

                    //  Lista de productos
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(cart.products, key = { it.id }) { product ->
                            val density = LocalDensity.current
                            val dismissState = rememberSwipeToDismissBoxState(
                                positionalThreshold = {
                                    with(density) { 100.dp.toPx() }
                                },
                                confirmValueChange = { value ->
                                    if (value == SwipeToDismissBoxValue.EndToStart) {
                                        itemToRemove = product.id
                                        false
                                    } else false
                                }
                            )

                            SwipeToDismissBox(
                                state = dismissState,
                                backgroundContent = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(16.dp)),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_trash),
                                            contentDescription = "Delete",
                                            tint = Color.Red,
                                            modifier = Modifier.padding(end = 24.dp)
                                        )
                                    }
                                },
                                content = {
                                    CartItemCard(
                                        imageUrl = product.thumbnail,
                                        title = product.title,
                                        subtitle = "Qty: ${product.quantity}",
                                        price = product.price
                                    )
                                }
                            )
                        }
                    }

                    //  Panel inferior
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(245.dp)
                            .padding(bottom = 12.dp),
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

//                            Button(
//                                onClick = { navController.navigate(Destinations.CHOOSE_PAYMENT_METHOD) },
                            Button(
                                onClick = {
                                    state.cart?.let { viewModel.guardarCarritoEnFirestore(it) }
                                    navController.navigate(Destinations.CHOOSE_PAYMENT_METHOD)
                                },
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

        if (itemToRemove != null) {
            AlertDialog(
                onDismissRequest = { itemToRemove = null },
                confirmButton = {
                    TextButton(onClick = {
                        itemToRemove?.let { viewModel.removeFromCart(it) }
                        itemToRemove = null
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { itemToRemove = null }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Delete product?") },
                text = { Text("Remove product from Cart?") }
            )
        }
    }
}
