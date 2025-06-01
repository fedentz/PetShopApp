package com.fedenintzel.petshopapp.presentation.screen.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.fedenintzel.petshopapp.R
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSheet(
    show: Boolean,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    if (show) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            dragHandle = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFFDDDDDD))
                    )
                }
            }
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    "Location",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                var search by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    placeholder = {
                        Text(
                            "Search your Location",
                            color = Color(0xFFB3B1B0)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_custom),
                            contentDescription = "Search",
                            tint = Color(0xFFB3B1B0)
                        )
                    },
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )
                Divider(modifier = Modifier.padding(vertical = 20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location_custom),
                        contentDescription = null,
                        tint = Color(0xFF7140FD),
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            "Track your Location",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            "automatically selects your\ncurrent location",
                            color = Color(0xFFB3B1B0),
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

