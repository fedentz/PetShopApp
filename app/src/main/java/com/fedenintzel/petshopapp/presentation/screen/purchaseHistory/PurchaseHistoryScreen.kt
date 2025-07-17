package com.fedenintzel.petshopapp.presentation.screen.purchaseHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.components.PurchaseCard
import com.fedenintzel.petshopapp.presentation.viewModel.CartViewModel
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun PurchaseHistoryScreen(
    userId: String,
    viewModel: CartViewModel,
    onBack: () -> Unit
) {
    val isLoading = viewModel.isLoadingHistory
    val history = viewModel.purchaseHistory

    LaunchedEffect(true) {
        println("🔍 USER ID en pantalla: $userId")
        viewModel.loadPurchaseHistory(userId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                            .asPaddingValues()
                    )
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = onBack,
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
                    text = "Purchase History",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // Contenido principal
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    items(history) { cart ->
                        PurchaseCard(cart)
                    }
                }
            }
        }
    }
}
