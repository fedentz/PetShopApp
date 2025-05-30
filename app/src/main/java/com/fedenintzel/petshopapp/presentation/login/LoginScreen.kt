package com.fedenintzel.petshopapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.viewmodel.LoginUiState

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onLoginClick: (String, String) -> Unit,
    onCreateAccountClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showValidationError by remember { mutableStateOf(false) }
    val isFormValid = email.isNotBlank() && password.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Hello, Welcome Back!",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Water is life. Water is a basic human need. In various lines of life, humans need water.",
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Email field with validation
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                if (showValidationError) showValidationError = false
            },
            placeholder = { Text("Email") },
            singleLine = true,
            isError = showValidationError && email.isBlank(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        if (showValidationError && email.isBlank()) {
            Text(
                text = "Required field",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Password field with validation
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                if (showValidationError) showValidationError = false
            },
            placeholder = { Text("Password") },
            singleLine = true,
            isError = showValidationError && password.isBlank(),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        if (showValidationError && password.isBlank()) {
            Text(
                text = "Required field",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Social buttons (non-functional)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Google",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF337EFE)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "facebook",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF337EFE)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Link to Create Account
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account? ")
            Text(
                text = "Create Account",
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable() { onCreateAccountClick() }
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        // Show network or login errors
        uiState.errorMessage?.let { errorMsg ->
            Text(
                text = errorMsg,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        }

        // Login button with loading indicator
        Button(
            onClick = {
                if (isFormValid) {
                    onLoginClick(email, password)
                } else {
                    showValidationError = true
                }
            },
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            } else {
                Text(
                    text = "Get Started",
                    color = if (isFormValid) Color.White else Color.LightGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState()
    MaterialTheme {
        LoginScreen(
            uiState = uiState,
            onLoginClick = { _, _ -> },
            onCreateAccountClick = {}
        )
    }
}
