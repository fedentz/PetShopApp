package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.domain.model.settings.SettingsCategory
import com.fedenintzel.petshopapp.domain.model.settings.SettingsItem
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.presentation.components.SettingsBaseScreen
import com.fedenintzel.petshopapp.presentation.components.SettingsItemRow
import com.fedenintzel.petshopapp.presentation.viewmodel.SessionViewModel
import com.fedenintzel.petshopapp.ui.theme.Poppins

/**
 * Pantalla de configuración (Settings) principal.
 *
 * Esta pantalla muestra una lista agrupada de categorías con ítems de configuración.
 *
 * La estructura de la pantalla incluye:
 * - Un encabezado reutilizable con top bar (ver `SettingsBaseScreen`)
 * - Una lista de secciones agrupadas por categoría (Account, Help, etc.)
 * - Cada ítem puede tener un ícono, un título, y un contenido al final (por ejemplo, un ícono de flecha o un switch)
 *
 * @param categories Lista de categorías con ítems de configuración. Por defecto se obtiene de un proveedor de datos fake.
 * @param onBackClick Acción que se ejecuta al presionar el botón de retroceso.
 * @param onItemClick Acción a ejecutar cuando se presiona un ítem. Se pasa el ítem seleccionado como parámetro.
 *
 * Uso típico:
 * ```
 * SettingsPageScreen(
 *     onBackClick = { navController.popBackStack() },
 *     onItemClick = { item -> /* navegar o ejecutar acción */ }
 * )
 * ```
 *
 * Ejemplo de contenido de `SettingsCategory`:
 * - category: "Account"
 * - items: [SettingsItem("Profile", iconResId = R.drawable.ic_profile, ...)]
 */

@Composable
fun SettingsPageScreen(

    navController: NavController,
    categories: List<SettingsCategory> = FakeSettingsDataProvider.getSettingsCategories(),
    onBackClick: () -> Unit,
    onItemClick: (SettingsItem) -> Unit
) {
    val sessionViewModel: SessionViewModel = hiltViewModel()

    SettingsBaseScreen(
        title = "Settings Page",
        onBackClick = onBackClick,
        showBottomButton = true,
        bottomButtonText = "Log Out",
        onBottomButtonClick = {
            sessionViewModel.logout()
            navController.navigate(Destinations.LOGIN) {popUpTo(0) }
                              },
        bottomButtonFilled = false
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            categories.forEach { category ->
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = category.title,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    category.items.forEach { item ->
                        SettingsItemRow(
                            title = item.title,
                            icon = item.iconResId,
                            endContent = item.trailingContent,
                            onClick = { onItemClick(item) }                        )
                    }
                }
            }
        }
    }
}

