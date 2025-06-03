package com.fedenintzel.petshopapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PetShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()


    }
}