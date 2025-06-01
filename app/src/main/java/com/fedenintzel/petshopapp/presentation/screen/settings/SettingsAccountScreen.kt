package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.presentation.components.ProfileHeader
import com.fedenintzel.petshopapp.presentation.components.SettingsBaseScreen
import com.fedenintzel.petshopapp.presentation.components.SettingsTextField
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme

/**
 * Pantalla de edición de cuenta dentro de la sección Settings.
 *
 * Muestra el header del perfil y permite editar:
 * - Nombre
 * - Nombre de usuario
 * - Email
 *
 * Incluye un botón inferior para guardar cambios.
 */

@Composable
fun SettingsAccountScreen(
    onBackClick: () -> Unit,
    onSaveChanges: (String, String, String) -> Unit = { _, _, _ -> }
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    SettingsBaseScreen(
        title = "Account",
        onBackClick = onBackClick,
        showBottomButton = true,
        bottomButtonText = "Save Changes",
        bottomButtonFilled = true,
        onBottomButtonClick = {
            onSaveChanges(name, username, email)
        }
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        ProfileHeader(
            backgroundPainter = painterResource(id = R.drawable.profile_background),
            profileImage = painterResource(id = R.drawable.profile_avatar),
            userName = "Abduldul",
            isEditable = true,
            onEditAvatarClick = {},
            onEditBackgroundClick = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            SettingsTextField(
                label = "Name",
                value = name,
                onValueChange = { name = it }
            )
            SettingsTextField(
                label = "Username",
                value = username,
                onValueChange = { username = it }
            )
            SettingsTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsAccountScreenPreview() {
    PetShopAppTheme {
        SettingsAccountScreen(
            onBackClick = {}
        )
    }
}
