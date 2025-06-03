package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import com.fedenintzel.petshopapp.ui.theme.Poppins

/**
 * Componente que muestra una fila de métricas como "Followers", "Following" y "Sales"
 *
 * @param followers Número de seguidores
 * @param following Número de seguidos
 * @param sales Número de ventas
 */
@Composable
fun StatsRow(
    followers: Int,
    following: Int,
    sales: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.width(224.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(number = followers, label = "Followers")
        StatItem(number = following, label = "Following")
        StatItem(number = sales, label = "Sales")
    }
}

@Composable
private fun StatItem(number: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(-4.dp)
    ) {
        Text(
            text = number.toString(),
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = label,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF595959)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatsRowPreview(){
    PetShopAppTheme {
        StatsRow(
            followers = 109,
            following = 992,
            sales = 80
        )
    }
}