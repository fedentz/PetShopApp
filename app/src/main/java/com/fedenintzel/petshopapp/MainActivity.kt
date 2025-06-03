package com.fedenintzel.petshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.fedenintzel.petshopapp.presentation.navigation.NavigationWrapper
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            setContent {
                PetShopAppTheme {
                    NavigationWrapper()

                }

            }
        }

    }
}
