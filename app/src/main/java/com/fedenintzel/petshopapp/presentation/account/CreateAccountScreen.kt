package com.fedenintzel.petshopapp.presentation.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import kotlinx.coroutines.flow.StateFlow

/**
 * Composable que muestra la pantalla de "Create Account".
 *
 * @param uiState estado provisto por CreateAccountViewModel.
 * @param onCreateAccountClick callback que recibe (fullName, email, password, agreed).
 * @param onLoginClick callback que se dispara cuando se toca "Login" en la parte inferior.
 */
@Composable
fun CreateAccountScreen(
    uiState: StateFlow<CreateAccountUiState>,
    onCreateAccountClick: (String, String, String, Boolean) -> Unit,
    onLoginClick: () -> Unit
) {
    // Variables locales que reflejan el estado
    var fullName by remember { mutableStateOf(uiState.firstName + " " + uiState.lastName) }
    var email by remember { mutableStateOf(uiState.email) }
    var password by remember { mutableStateOf(uiState.password) }
    var agreed by remember { mutableStateOf(uiState.agreed) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // ─── Título “Create New Account” ───
            Text(
                text = "Create New Account",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ─── Texto de descripción “Water is life...” ───
            Text(
                text = "Water is life. Water is a basic human need. In various lines of life, humans need water.",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = Color(0xFF898989)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ─── Campo “Full Name” ───
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = { Text(text = "Full Name", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ─── Campo “Email” ───
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "Email", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ─── Campo “Password” ───
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Password", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ─── Checkbox “I Agree to the Terms of Service and Privacy Policy” ───
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { agreed = !agreed }
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = agreed,
                    onCheckedChange = { agreed = it },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "I Agree to the Terms of Service and Privacy Policy",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = Color(0xFF4C2AED), // morado
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ─── Texto “Have an account? Login” ───
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Have an account? ",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = Color.Black
                )
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = Color(0xFF4C2AED),
                    modifier = Modifier
                        .clickable { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ─── Botón “Get Started” ───
            Button(
                onClick = {
                    onCreateAccountClick(
                        fullName.trim(),
                        email.trim(),
                        password,
                        agreed
                    )
                },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Get Started",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ─── Mensaje de error (si existe) ───
            uiState.errorMessage?.let { error ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = error,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = Color.Red
                    )
                }
            }
        }
    }
}
