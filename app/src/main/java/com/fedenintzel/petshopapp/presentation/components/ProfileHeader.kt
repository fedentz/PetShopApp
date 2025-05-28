package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun ProfileHeader(
    backgroundPainter: Painter,
    profileImage: Painter,
    userName: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(327.dp)
                .height(159.dp + 50.dp), // Box + Imagen de perfil
            contentAlignment = Alignment.TopCenter
        ) {
            // Fondo
            Image(
                painter = backgroundPainter,
                contentDescription = "Profile Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(327.dp)
                    .height(159.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .align(Alignment.TopCenter)
            )

            // Imagen de perfil centrada abajo
            Image(
                painter = profileImage,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = userName,
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center ,
            )

    }
}





@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview(){
    PetShopAppTheme {
        ProfileHeader(
            backgroundPainter = painterResource(id = R.drawable.profile_background),
            profileImage = painterResource(id = R.drawable.profile_avatar),
            userName = "Abduldul"
        )
    }
}
