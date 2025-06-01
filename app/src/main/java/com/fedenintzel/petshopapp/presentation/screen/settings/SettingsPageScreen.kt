package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.Poppins

@Composable
fun SettingsPageScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(bottom = 100.dp)
                .padding(top = 40.dp)
        ) {
            Header()
            Spacer(modifier = Modifier.height(24.dp))
            AccountSettings()
            Spacer(modifier = Modifier.height(32.dp))
            HelpSettings()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            ButtonLogOut()
        }
    }
}

@Composable
fun Header() {
    Box(modifier = Modifier.fillMaxWidth().height(56.dp)) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .align(Alignment.CenterStart)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Back",
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = "Settings Page",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun AccountSettings() {
    Text(
        text = "Account",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Poppins,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SettingsItem(R.drawable.ic_profile, "Account")
        SettingsItem(R.drawable.ic_home, "Address")
        SettingsItem(R.drawable.ic_notification, "Notification")
        SettingsItem(R.drawable.ic_wallet, "Payment Method")
        SettingsItem(R.drawable.ic_dangercircle, "Privacy")
        SettingsItem(R.drawable.ic_password, "Security")
    }
}

@Composable
fun HelpSettings() {
    Text(
        text = "Help",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Poppins,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SettingsItem(R.drawable.ic_call, "Contact Us")
        SettingsItem(R.drawable.ic_document, "FAQ")
    }
}

@Composable
fun SettingsItem(icon: Int, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFE7E7E7), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(22.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = Poppins,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Arrow",
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun ButtonLogOut() {
    OutlinedButton(
        onClick = { },
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, Color(0xFF7A4FFF)),
        modifier = Modifier
            .width(327.dp)
            .height(56.dp)
    ) {
        Text(
            text = "Log Out",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7A4FFF),
            fontFamily = Poppins
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsPageScreenPreview() {
    SettingsPageScreen()
}
