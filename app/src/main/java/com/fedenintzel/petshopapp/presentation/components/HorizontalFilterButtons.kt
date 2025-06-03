package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import com.fedenintzel.petshopapp.ui.theme.Poppins


/*
* Componente de botones tipo filtro, con opción de incluir un ícono como primer botón
*
* El botón activo se destaca con fondo morado (#7140FD) y texto blanco.
* Los botones inactivos tienen fondo gris claro (#F8F8F8) y texto gris (#B3B1B0)
*
* @param options Lista de pares (texto del botón, acción opcional). El primero puede ser un ícono (texto se ignora si hay ícono)
* @param selectedIndex Índice del botón actualmente activo
* @param onSelect Lógica a ejecutar al seleccionar un botón
* @param leadingIconForFirstItem Ícono exclusivo que se renderiza solo en el primer botón
*/

@Composable
fun HorizontalFilterButtons(
    options: List<Pair<String, (() -> Unit)?>>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    leadingIconForFirstItem: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier

    ) {
        val actualOptions = if (leadingIconForFirstItem != null && options.isNotEmpty()) {
            listOf("" to null) + options
        } else {
            options
        }

        actualOptions.forEachIndexed { index, (label, action) ->
            val isSelected = index == selectedIndex

            val backgroundColor = if (isSelected) Color(0xFF7140FD) else Color(0xFFF8F8F8)
            val contentColor = if (isSelected) Color.White else Color(0xFFB3B1B0)

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor)
                    .clickable {
                        onSelect(index)
                        action?.invoke()
                    }
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                if (index == 0 && leadingIconForFirstItem != null) {
                    leadingIconForFirstItem()
                } else {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Poppins,
                        color = contentColor
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HorizontalFilterButtonsPreview(){
    PetShopAppTheme {
        val selectedIndex = 1

        HorizontalFilterButtons(
            options = listOf(
                "Saved" to null,
                "Edit Profile" to { /* Alguna acción */ }
            ),
            selectedIndex = selectedIndex,
            onSelect = {  },
            leadingIconForFirstItem = {
                Image(
                    painter = painterResource(id = R.drawable.swap_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
    }
}


