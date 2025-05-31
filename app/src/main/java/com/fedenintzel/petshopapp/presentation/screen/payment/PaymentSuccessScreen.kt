package com.fedenintzel.petshopapp.presentation.screen.payment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun PaymentSuccessScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            Description()
        }

        GoHomeButton()
    }
}

@Composable
fun Title() {
    Text(
        text = "Payment\n \nSuccess!",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Poppins,
        color = Color.Black
    )
}

@Composable
fun Description() {
    Text(
        text = "your order is being prepared by the shop, the courier will send it to your address",
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = Poppins,
        color = Color.Gray
    )
}

@Composable
fun GoHomeButton() {
    Button(
        onClick = { /* TODO: Navegar a Home */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF7140FD),
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Go Home",
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentSuccessScreenPreview() {
    PaymentSuccessScreen()
}
