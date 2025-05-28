package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun SegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(240.dp)
            .height(45.dp)
            .clip(RoundedCornerShape(22.5.dp))
            .background(Color(0xFFF8F8F8))
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEachIndexed { index, option ->
                val isSelected = index == selectedIndex
                Box(
                    modifier = Modifier
                        .width(114.dp)
                        .height(37.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(if (isSelected) Color(0xFF7140FD) else Color.Transparent)
                        .clickable { onOptionSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Poppins,
                        color = if (isSelected) Color.White else Color(0xFFB3B1B0)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SegmentedControlPreview (){
    PetShopAppTheme {

        SegmentedControl(
            options = listOf("Profile", "Seller Mode"),
            selectedIndex = 1,
            onOptionSelected = {}
        )
    }
}
