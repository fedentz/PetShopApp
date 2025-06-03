package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.runtime.Composable
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

