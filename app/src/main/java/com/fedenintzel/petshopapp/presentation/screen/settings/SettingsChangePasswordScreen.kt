package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fedenintzel.petshopapp.presentation.components.SettingsBaseScreen
import androidx.compose.ui.tooling.preview.Preview
import com.fedenintzel.petshopapp.presentation.components.SettingsTextField
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme

/**
 * Pantalla para cambio de contraseña dentro de la sección de Settings.
 *
 * Usa la pantalla base de Settings y define dos campos:
 * - Nueva contraseña
 * - Confirmación de contraseña
 *
 * El botón inferior ejecuta una acción de guardado (mockeado por ahora).
 */
@Composable
fun SettingsChangePasswordScreen(
    onBackClick: () -> Unit,
    onChangePassword: (String, String) -> Unit = { _, _ -> }
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    SettingsBaseScreen(
        title = "Change Password",
        onBackClick = onBackClick,
        showBottomButton = true,
        bottomButtonText = "Change Password",
        bottomButtonFilled = true,
        onBottomButtonClick = {
            // Acá se puede validar y llamar a ViewModel o lógica externa
            onChangePassword(newPassword, confirmPassword)
        }
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

            SettingsTextField(
                label = "New Password",
                value = newPassword,
                onValueChange = { newPassword = it }
            )

            SettingsTextField(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsChangePasswordPreview(){
    PetShopAppTheme {
        SettingsChangePasswordScreen(

            onBackClick = {}
        )
    }
}
