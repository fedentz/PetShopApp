package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.Poppins

/**
 * Componente visual que representa un ítem del carrito.
 * Incluye imagen (local o remota), título, subtítulo y precio.
 *
 * @param imageUrl URL de la imagen del producto (usado por defecto si no es nulo)
 * @param imageResId Recurso local de imagen (usado en previews o cuando imageUrl es nulo)
 * @param title Título del producto
 * @param subtitle Subtítulo del producto
 * @param price Precio individual del producto
 */

@Composable
fun CartItemCard(
    imageUrl: String? = null,
    imageResId: Int? = null,
    title: String,
    subtitle: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contenedor imagen
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF8F8F8)),
                contentAlignment = Alignment.Center
            ) {
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                } else if (imageResId != null) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Detalles del producto
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = subtitle,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color(0xFFB3B1B0)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "$${"%.2f".format(price)}",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(0xFF7140FD)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemCardPreview() {
    CartItemCard(
        imageResId = R.drawable.product_image,
        title = "Royal Canin Adult",
        subtitle = "for 2–3 years",
        price = 12.99
    )
}
