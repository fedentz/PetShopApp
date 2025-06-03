package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import androidx.compose.foundation.clickable

@Composable
fun Header(
    userName: String = "Jebres, Surakarta",
    onLocationClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column{
            // "Location" y flecha
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onLocationClick() }
            ) {
                Text(
                    text = "Location",
                    fontSize = 12.sp,
                    color = Color(0xFFB3B1B0)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "Expand location",
                    modifier = Modifier
                        .size(16.dp)
                        .padding(start = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = userName,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row {
            IconButton(onClick = onSearchClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search_custom),
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = onNotificationsClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bell_custom),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

