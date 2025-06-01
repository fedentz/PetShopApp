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

enum class NotificationTab { ACTIVITY, SELLER }

@Composable
fun NotificationsScreen(onBack: () -> Unit) {
    var selectedTab by remember { mutableStateOf(NotificationTab.ACTIVITY) }

    // Notificaciones para "Activity"
    val activityNotifications = listOf(
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "SALE 50%",
            subtitle = "Check the details!",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "SALE 50%",
            subtitle = "Check the details!",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "SALE 50%",
            subtitle = "Check the details!",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "SALE 50%",
            subtitle = "Check the details!",
            rightType = SellerNotification.RightType.Arrow
        )
    )

    // Notificaciones para "Seller Mode"
    val sellerNotifications = listOf(
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "You Got New Order!",
            subtitle = "Please arrange delivery",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.avatar_momon,
            title = "Momon",
            subtitle = "Liked your Product",
            rightType = SellerNotification.RightType.Image(R.drawable.comidabanner)
        ),
        SellerNotification(
            leftImage = R.drawable.avatar_ola,
            title = "Ola",
            subtitle = "Liked your Product",
            rightType = SellerNotification.RightType.Image(R.drawable.comidabanner)
        ),
        SellerNotification(
            leftImage = R.drawable.avatar_raul,
            title = "Raul",
            subtitle = "Liked your Product",
            rightType = SellerNotification.RightType.Image(R.drawable.comidabanner)
        ),
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "You Got New Order!",
            subtitle = "Please arrange delivery",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "You Got New Order!",
            subtitle = "Please arrange delivery",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.comidabanner,
            title = "You Got New Order!",
            subtitle = "Please arrange delivery",
            rightType = SellerNotification.RightType.Arrow
        ),
        SellerNotification(
            leftImage = R.drawable.avatar_vito,
            title = "Vito",
            subtitle = "Liked your Product",
            rightType = SellerNotification.RightType.Image(R.drawable.comidabanner)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Encabezado: Flecha y título
        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
        ) {
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
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                "Notification",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Toggle centrado
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
                        .background(if (selectedTab == NotificationTab.ACTIVITY) Color(0xFF7140FD) else Color.Transparent)
                        .clickable { selectedTab = NotificationTab.ACTIVITY }
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Activity",
                        color = if (selectedTab == NotificationTab.ACTIVITY) Color.White else Color(0xFFB3B1B0),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                // Seller Mode tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (selectedTab == NotificationTab.SELLER) Color(0xFF7140FD) else Color.Transparent)
                        .clickable { selectedTab = NotificationTab.SELLER }
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Seller Mode",
                        color = if (selectedTab == NotificationTab.SELLER) Color.White else Color(0xFFB3B1B0),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de notificaciones
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            val notifications = when (selectedTab) {
                NotificationTab.ACTIVITY -> activityNotifications
                NotificationTab.SELLER -> sellerNotifications
            }
            notifications.forEach { notif ->
                SellerNotificationRow(notif)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

// Reutilizable para ambas pestañas
data class SellerNotification(
    val leftImage: Int,
    val title: String,
    val subtitle: String,
    val rightType: RightType
) {
    sealed class RightType {
        object Arrow : RightType()
        data class Image(val img: Int) : RightType()
    }
}

@Composable
fun SellerNotificationRow(notification: SellerNotification) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = notification.leftImage),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                notification.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                notification.subtitle,
                color = Color(0xFFB3B1B0),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        when (notification.rightType) {
            is SellerNotification.RightType.Arrow -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Details",
                    modifier = Modifier.size(20.dp)
                )
            }
            is SellerNotification.RightType.Image -> {
                Image(
                    painter = painterResource(id = notification.rightType.img),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5))
                )
            }
        }
    }
}

