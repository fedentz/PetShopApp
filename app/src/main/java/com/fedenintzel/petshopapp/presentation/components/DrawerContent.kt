package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.presentation.screen.settings.SettingsPageScreen
import com.fedenintzel.petshopapp.presentation.viewModel.CartViewModel
import com.fedenintzel.petshopapp.presentation.viewModel.SessionViewModel


@Composable
fun DrawerContent(
    navController: NavController,
    onSettingsClick: () -> Unit,
    sessionViewModel: SessionViewModel

) {
    SettingsPageScreen(
        categories = FakeSettingsDataProvider.getSettingsCategories(),
        onBackClick = {},
        onItemClick = { onSettingsClick() },
        navController = navController,
        sessionViewModel = sessionViewModel


    )
}

