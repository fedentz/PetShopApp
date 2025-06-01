package com.fedenintzel.petshopapp.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image

@Composable
fun SearchScreen(
    navController: NavController
) {
    val categories = listOf("Food", "Toys", "Accesories")
    val selectedCategory = "Food"
    val recentSearches = listOf(
        "Royal Canin Persian 500g",
        "Royal Canin Persian 500g",
        "Royal Canin Persian 500g"
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        // Top bar con back y título
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                shadowElevation = 8.dp,
                color = Color.White,
                modifier = Modifier.size(40.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(40.dp) // Tamaño del círculo de fondo
                            .background(Color.White, shape = CircleShape)
                            .clickable { navController.popBackStack() }
                            .padding(10.dp) // Para que la flecha quede de 20dp centrada
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "Search",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(40.dp)) // Para balancear el back
        }
        Spacer(modifier = Modifier.height(28.dp))
        // Buscador
        Surface(
            shape = RoundedCornerShape(18.dp),
            shadowElevation = 2.dp,
            border = BorderStroke(1.dp, Color(0xFFE5E5E5)),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 18.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_custom), // Tu ícono de lupita
                    contentDescription = "Search",
                    tint = Color(0xFFB3B1B0),
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Search your Product",
                    color = Color(0xFFB3B1B0),
                    fontSize = 16.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        // Categorías
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_swap_custom), // Tu ícono de swap
                contentDescription = "Swap",
                tint = Color(0xFFB3B1B0),
                modifier = Modifier
                    .size(34.dp)
                    .background(Color(0xFFF8F8F8), shape = CircleShape)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            categories.forEach { category ->
                val selected = category == selectedCategory
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (selected) Color(0xFF7140FD) else Color(0xFFF8F8F8))
                        .padding(horizontal = 22.dp, vertical = 10.dp)
                ) {
                    Text(
                        category,
                        color = if (selected) Color.White else Color(0xFFB3B1B0),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        // Recent y el historial
        Text(
            "Recent",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        recentSearches.forEach { search ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    search,
                    color = Color(0xFFB3B1B0),
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFF8F8F8),
                    shadowElevation = 0.dp,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close_custom), // Tu ícono de cerrar
                        contentDescription = "Remove",
                        tint = Color(0xFFB3B1B0),
                        modifier = Modifier
                            .size(18.dp)
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}