package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsPageScreen


@Composable
fun DrawerContent(
    navController: NavController,
    onSettingsClick: () -> Unit
) {
    SettingsPageScreen(
        categories = FakeSettingsDataProvider.getSettingsCategories(),
        onBackClick = {},
        onItemClick = { onSettingsClick() },
        navController = navController
    )
}

