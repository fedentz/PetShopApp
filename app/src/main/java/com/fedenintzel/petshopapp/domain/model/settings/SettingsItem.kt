package com.fedenintzel.petshopapp.domain.model.settings

import androidx.compose.runtime.Composable

/**
 * Representa un ítem de configuración dentro de la pantalla de Settings.
 *
 * @param title Título del ítem (ej: "Account", "Notification")
 * @param icon Ícono opcional del lado izquierdo (resource ID)
 * @param endContent Composable opcional del lado derecho (ej: flecha, switch)
 * @param onClick Acción a ejecutar cuando se toca el ítem
 */

data class SettingsItem(
    val title: String,
    val iconResId: Int? = null,
    val trailingContent: @Composable (() -> Unit)? = null,
    val onClick: () -> Unit = {}
)

