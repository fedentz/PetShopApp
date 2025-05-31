package com.fedenintzel.petshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fedenintzel.petshopapp.presentation.screen.payment.AddNewPaymentScreen
import com.fedenintzel.petshopapp.presentation.screen.payment.ChoosePaymentMethodScreen
import com.fedenintzel.petshopapp.presentation.screen.payment.PaymentSuccessScreen

import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetShopAppTheme {
                PaymentSuccessScreen()            }
        }
    }
}



