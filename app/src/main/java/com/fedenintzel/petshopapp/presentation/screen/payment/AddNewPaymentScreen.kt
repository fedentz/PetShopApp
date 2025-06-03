package com.fedenintzel.petshopapp.presentation.screen.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun AddNewPaymentScreen(
    navController: NavController
) {
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var expired by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var attemptedSubmit by remember { mutableStateOf(false) }

    val allFieldsFilled = cardNumber.isNotBlank() && cardName.isNotBlank() &&
            expired.isNotBlank() && cvv.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Add New Payment",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        PaymentField("Card Number", cardNumber) { cardNumber = it }
        Spacer(modifier = Modifier.height(8.dp))
        PaymentField("Card Name", cardName) { cardName = it }
        Spacer(modifier = Modifier.height(8.dp))
        PaymentField("Expired", expired) { expired = it }
        Spacer(modifier = Modifier.height(8.dp))
        PaymentField("CVV", cvv) { cvv = it }

        Spacer(modifier = Modifier.height(12.dp))

        if (!allFieldsFilled && attemptedSubmit) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error",
                    tint = Color.Red,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Required Fields",
                    color = Color.Red,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    fontFamily = Poppins
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                attemptedSubmit = true
                if (allFieldsFilled) {
                    navController.navigate(Destinations.SETTINGS)                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (allFieldsFilled) Color(0xFF7140FD) else Color(0xFFE0E0E0)
            )
        ) {
            Text(
                text = "Save",
                fontFamily = Poppins,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (allFieldsFilled) Color.White else Color.Gray
            )
        }
    }
}

@Composable
fun PaymentField(
    placeholderText: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            if (showError && it.isNotBlank()) showError = false
        },
        placeholder = {
            Text(placeholderText, color = Color.Gray, fontFamily = Poppins, fontSize = 14.sp)
        },
        isError = showError,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .onFocusChanged {
                if (isFocused && !it.isFocused && value.isBlank()) {
                    showError = true
                }
                isFocused = it.isFocused
            },
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {}),
        textStyle = TextStyle(
            fontFamily = Poppins,
            fontSize = 14.sp,
            color = Color(0xFF7140FD)
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = if (showError) Color.Red else Color(0xFF7140FD),
            unfocusedIndicatorColor = if (showError) Color.Red else Color(0xFFE0E0E0),
            errorIndicatorColor = Color.Red,
            focusedTextColor = Color(0xFF7140FD),
            unfocusedTextColor = Color(0xFF7140FD),
            cursorColor = Color(0xFF7140FD),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )

    if (showError) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "Error",
                tint = Color.Red,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Required Field",
                color = Color.Red,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                fontFamily = Poppins
            )
        }
    }
}

@Composable
fun Header() {
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

