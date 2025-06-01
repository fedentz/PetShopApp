package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.ui.theme.Poppins
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fedenintzel.petshopapp.R
import androidx.compose.material3.*
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme

/**
 * Fila que representa visualmente un ítem dentro de Settings.
 *
 * Incluye ícono opcional a la izquierda, texto y un composable opcional a la derecha.
 *
 * @param title Título a mostrar (ej: "Notification")
 * @param icon Ícono opcional (resource ID)
 * @param endContent Composable opcional (ej: flecha o switch)
 * @param onClick Acción cuando se toca la fila
 */
@Composable
fun SettingsItemRow(
    title: String,
    icon: Int? = null,
    endContent: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono a la izquierda
        if (icon != null) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE7E7E7)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
        }

        // Título
        Text(
            text = title,
            fontFamily = Poppins,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        // Contenido del extremo derecho (flecha, switch, etc.)
        endContent?.let {
            it()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsItemRowPreview() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SettingsItemRow(
            title = "Account",
            icon = R.drawable.ic_profile,
            endContent = {
                Image(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        )
    }
}