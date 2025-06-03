package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import androidx.compose.ui.layout.ContentScale

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 25.dp)
            .height(145.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(145.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(36.dp))
                .background(Color(0xFF7140FD))
                .align(Alignment.Center)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color(0xFF9F7CFF).copy(alpha = 0.29f),
                    radius = size.minDimension * 0.22f,
                    center = androidx.compose.ui.geometry.Offset(x = size.width * 0.18f, y = size.height * 0.23f)
                )
                drawCircle(
                    color = Color(0xFF9F7CFF).copy(alpha = 0.29f),
                    radius = size.minDimension * 0.19f,
                    center = androidx.compose.ui.geometry.Offset(x = size.width * 0.90f, y = size.height * 0.18f)
                )
                drawCircle(
                    color = Color(0xFF9F7CFF).copy(alpha = 0.29f),
                    radius = size.minDimension * 0.16f,
                    center = androidx.compose.ui.geometry.Offset(x = size.width * 0.80f, y = size.height * 0.82f)
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 0.dp)
                .height(145.dp)
                .width(210.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.comidabanner),
                contentDescription = "Royal Canin Big Bag",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(230.dp) // MÁS GRANDE
                    .align(Alignment.CenterStart)
                    .offset(x = (-30).dp, y = (-40).dp)
                    .zIndex(1f)
            )
            Image(
                painter = painterResource(R.drawable.comidabanner),
                contentDescription = "Royal Canin Small Bag",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(150.dp) // MÁS GRANDE
                    .align(Alignment.CenterStart)
                    .offset(x = 45.dp, y = 28.dp)
                    .zIndex(2f)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(start = 140.dp, end = 30.dp, top = 22.dp, bottom = 16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Royal Canin\nAdult Pomeraniann",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )
            Text(
                "Get an interesting promo\nhere, without conditions",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp
            )
        }
    }
}

