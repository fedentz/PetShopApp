package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun AccountSettingScreen() {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp, bottom = 100.dp)
        ) {
            ScreenHeader()
            Spacer(modifier = Modifier.height(24.dp))
            ProfileHeader(
                backgroundPainter = painterResource(id = R.drawable.profile_background),
                profileImage = painterResource(id = R.drawable.profile_avatar),
                userName = "Abduldul"
            )
            Spacer(modifier = Modifier.height(32.dp))

            TextFieldBlock("Name", name) { name = it }
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldBlock("Username", username) { username = it }
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldBlock("Email", email) { email = it }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            SaveChangesButton()
        }
    }
}

@Composable
fun ScreenHeader() {
    Box(modifier = Modifier.fillMaxWidth().height(56.dp)) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .clickable { }
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Back",
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = "Account",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

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
                .height(209.dp),
            contentAlignment = Alignment.TopCenter
        ) {
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

            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit Background",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(24.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(4.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(100.dp)
            ) {
                Image(
                    painter = profileImage,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .background(Color(0xFFEDEDED))
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit Avatar",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 6.dp, y = 7.dp)
                        .size(24.dp)
                        .background(Color.White, shape = CircleShape)
                        .padding(4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = userName,
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun TextFieldBlock(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Poppins,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = label,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .width(327.dp)
                .height(72.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            textStyle = TextStyle(
                fontFamily = Poppins,
                fontSize = 14.sp,
                color = Color.Black
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SaveChangesButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .width(327.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7140FD))
    ) {
        Text(
            text = "Save Changes",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountSettingsScreenPreview() {
    AccountSettingScreen()
}
