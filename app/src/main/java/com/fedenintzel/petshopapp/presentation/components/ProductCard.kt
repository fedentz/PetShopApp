package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fedenintzel.petshopapp.ui.theme.Poppins

/**
 * Componente que representa una tarjeta de producto
 *
 * @param name Nombre del producto (ej: "RC Kitten")
 * @param price Precio del producto (Ej: 20.99)
 * @param imageResId Recurso de imagen del producto (drawable)
 * @param onAddClick Acción al presionar el botón "+" (Ej: agregar al carrito)
 * @param onCardClick Acción al presionar la tarjeta completa (por Ej: navegar a detalles)
 */
@Composable
fun ProductCard(
    name: String,
    price: Double,
    imageResId: Int,
    onAddClick: () -> Unit,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(width = 156.dp, height = 210.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFF8F8F8))
            .clickable { onCardClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(142.dp)
        )

        // Nombre + Precio + Botón "+"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                Text(
                    text = "$${"%.2f".format(price)}",
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF7140FD))
                    .clickable { onAddClick() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "Add",
                    modifier = Modifier.size(16.dp)
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        name = "RC Kitten",
        price = 20.99,
        imageResId = R.drawable.product_image,
        onAddClick = { /* Agregar al carrito */ },
        onCardClick = { /* Navegar a ProductDetails */ }
    )
}