package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedenintzel.petshopapp.presentation.components.SettingsBaseScreen
import com.fedenintzel.petshopapp.presentation.components.SettingsTextField
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme

/**
 * Pantalla para cambio de email dentro de la sección de Settings.
 *
 * Usa la pantalla base de Settings y define un campo de entrada:
 * - Nuevo Email
 *
 * El botón inferior ejecuta una acción de guardado (mockeado por ahora).
 */
@Composable
fun SettingsChangeEmailScreen(
    onBackClick: () -> Unit,
    onSaveEmail: (String) -> Unit = {}
) {
    var email by remember { mutableStateOf("") }

    SettingsBaseScreen(
        title = "Change Email",
        onBackClick = onBackClick,
        showBottomButton = true,
        bottomButtonText = "Save",
        bottomButtonFilled = true,
        onBottomButtonClick = {
            onSaveEmail(email)
        }
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        SettingsTextField(
            label = "New Email",
            value = email,
            onValueChange = { email = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsChangeEmailPreview() {
    PetShopAppTheme {
        SettingsChangeEmailScreen(
            onBackClick = {}
        )
    }
}
