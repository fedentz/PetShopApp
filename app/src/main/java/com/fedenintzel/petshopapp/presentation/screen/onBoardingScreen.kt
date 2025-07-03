package com.fedenintzel.petshopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.Poppins
import kotlin.coroutines.Continuation

@Composable
fun OnBoardingScreen(onContinue: () -> Unit) {
    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 48.dp)
            ) {
                Title()
                Spacer(modifier = Modifier.height(16.dp))
                ImgOnBoarding()
                Spacer(modifier = Modifier.height(16.dp))
                Slogan()
                Spacer(modifier = Modifier.height(32.dp))
                CarrouselDots()
                Spacer(modifier = Modifier.height(32.dp))
                GetStartedButton(onClick = onContinue)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun Title() {
    Text(
        text = "Meet your\nanimal needs\nhere",
        color = Color.Black,
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,

    )
}

@Composable
fun ImgOnBoarding() {
    Image(
        painter = painterResource(id = R.drawable.illustration),
        contentDescription = "Onboarding Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Slogan() {
    Text(
        text = "Get interesting promos here, register your account immediately so you can meet your animal needs.",
        modifier = Modifier.padding(top = 16.dp),
        color = Color.Gray,
        fontFamily = Poppins,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )
}

@Composable
fun PageIndicators(
    totalDots: Int = 3,
    selectedIndex: Int = 0,
    activeColor: Color = Color(0xFF7140FD),
    inactiveColor: Color = Color(0xFFE0E0E0),
    dotSize: Dp = 10.dp,
    spacing: Dp = 10.dp
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .background(
                        color = if (index == selectedIndex) activeColor else inactiveColor,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun CarrouselDots() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        PageIndicators(selectedIndex = 0)
    }
}

@Composable
fun GetStartedButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7140FD)),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            "Get Started",
            color = Color.White,
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview
fun PreviewOnBoardingScreen() {
    OnBoardingScreen(onContinue = {})
}