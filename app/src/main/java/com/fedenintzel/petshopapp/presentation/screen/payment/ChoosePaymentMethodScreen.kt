package com.fedenintzel.petshopapp.presentation.screen.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.navigation.Destinations
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun ChoosePaymentMethodScreen(
    navController: NavController
) {
    var selectedOption by remember { mutableStateOf("") }

    val options = listOf("Paypal", "Bank Transfer")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        PaymentMethodHeader()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Choose your Payment Method",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        options.forEach { option ->
            PaymentOptionItem(
                label = option,
                selected = selectedOption == option,
                onClick = { selectedOption = option }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Destinations.PAYMENT_SUCCESS) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(50.dp),
            enabled = selectedOption.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption.isNotEmpty()) Color(0xFF7140FD) else Color(0xFFE0E0E0),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFE0E0E0),
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = "Checkout",
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun PaymentMethodHeader() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 40.dp)
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                .clickable { /* TODO: handle navigation */ }
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = "Payment Method",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.Black
        )
    }
}

@Composable
fun PaymentOptionItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (selected) Color(0xFF7140FD) else Color(0xFFE0E0E0)
    val textColor = if (selected) Color(0xFF7140FD) else Color.Gray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .border(1.dp, borderColor, shape = RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = textColor,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF7140FD),
                unselectedColor = Color.Gray
            )
        )
    }
}


