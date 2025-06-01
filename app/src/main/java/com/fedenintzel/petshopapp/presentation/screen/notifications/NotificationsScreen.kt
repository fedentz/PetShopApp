package com.fedenintzel.petshopapp.presentation.screen.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R

@Composable
fun NotificationsScreen(
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Activity") }
    val notifications = List(4) {
        NotificationItem(
            imageRes = R.drawable.comidabanner,
            title = "SALE 50%",
            subtitle = "Check the details!"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Row
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
        ) {
            // Flecha back
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back), // tu recurso de flecha
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp)
                )
            }
            // Titulo
            Text(
                "Notification",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Toggle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFF5F5F5))
            ) {
                // Activity tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (selectedTab == "Activity") Color(0xFF7140FD) else Color.Transparent)
                        .clickable { selectedTab = "Activity" }
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Activity",
                        color = if (selectedTab == "Activity") Color.White else Color(0xFFB3B1B0),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                // Seller Mode tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (selectedTab == "Seller Mode") Color(0xFF7140FD) else Color.Transparent)
                        .clickable { selectedTab = "Seller Mode" }
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Seller Mode",
                        color = if (selectedTab == "Seller Mode") Color.White else Color(0xFFB3B1B0),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de notificaciones
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            notifications.forEach { notif ->
                NotificationRow(notif)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

data class NotificationItem(
    val imageRes: Int,
    val title: String,
    val subtitle: String
)

@Composable
fun NotificationRow(item: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
        )
        Spacer(modifier = Modifier.width(12.dp))
        // Título y subtítulo
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                item.subtitle,
                color = Color(0xFFB3B1B0),
                fontSize = 14.sp
            )
        }
        // Flecha
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Details",
            modifier = Modifier.size(20.dp)
        )
    }
}

