package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import com.fedenintzel.petshopapp.R

enum class BottomBarItem {
    HOME, TIME, BAG, PROFILE
}

@Composable
fun BottomBar(
    selected: BottomBarItem = BottomBarItem.HOME,
    onHomeClick: () -> Unit = {},
    onTimeClick: () -> Unit = {},
    onBagClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xFFF8F8F8),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarIcon(
                iconRes = R.drawable.ic_home_custom,
                selected = selected == BottomBarItem.HOME,
                contentDescription = "Home",
                onClick = onHomeClick,
                modifier = Modifier.weight(1f)
            )
            BottomBarIcon(
                iconRes = R.drawable.ic_time_custom,
                selected = selected == BottomBarItem.TIME,
                contentDescription = "Time",
                onClick = onTimeClick,
                modifier = Modifier.weight(1f)
            )
            BottomBarIcon(
                iconRes = R.drawable.ic_bag_custom,
                selected = selected == BottomBarItem.BAG,
                contentDescription = "Cart",
                onClick = onBagClick,
                modifier = Modifier.weight(1f)
            )
            BottomBarIcon(
                iconRes = R.drawable.ic_profile_custom,
                selected = selected == BottomBarItem.PROFILE,
                contentDescription = "Profile",
                onClick = onProfileClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarIcon(
    iconRes: Int,
    selected: Boolean,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(22.dp),
            colorFilter = if (selected)
                ColorFilter.tint(Color(0xFF7140FD))
            else
                ColorFilter.tint(Color(0xFFB3B1B0))
        )
        if (selected) {
            Box(
                Modifier
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF7140FD))
            )
        } else {
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
