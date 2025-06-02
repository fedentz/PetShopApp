package com.fedenintzel.petshopapp.navigation.settings

import SettingsDestinations.SETTINGS_ACCOUNT_SCREEN
import SettingsDestinations.SETTINGS_FAQ_SCREEN
import SettingsDestinations.SETTINGS_NOTIFICATIONS_SCREEN
import SettingsDestinations.SETTINGS_PAGE_SCREEN
import SettingsDestinations.SETTINGS_PRIVACY_SCREEN
import SettingsDestinations.SETTINGS_SECURITY_SCREEN
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.presentation.screen.settings.*

@Composable
fun SettingsNavGraph(
    onBackClick: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SETTINGS_PAGE_SCREEN
    ) {
        composable(SETTINGS_PAGE_SCREEN) {
            SettingsPageScreen(
                categories = FakeSettingsDataProvider.getSettingsCategories(),
                onBackClick = onBackClick,
                onItemClick = { item ->
                    val route = when (item.title) {
                        "Account" -> SETTINGS_ACCOUNT_SCREEN
                        "Notification" -> SETTINGS_NOTIFICATIONS_SCREEN
                        "Privacy" -> SETTINGS_PRIVACY_SCREEN
                        "Security" -> SETTINGS_SECURITY_SCREEN
                        "FAQ" -> SETTINGS_FAQ_SCREEN
                        else -> SETTINGS_PAGE_SCREEN
                    }
                    navController.navigate(route)
                }
            )
        }

        composable(SETTINGS_ACCOUNT_SCREEN) {
            SettingsAccountScreen(
                onBackClick = { navController.popBackStack() },
                onSaveChanges = { name, username, email ->
                    // lógica para guardar cambios
                }
            )
        }

        composable(SETTINGS_NOTIFICATIONS_SCREEN) {
            SettingsNotificationsScreen(
                onBackClick = { navController.popBackStack() },
                categories = FakeSettingsDataProvider.getSettingsNotifications(),
                onItemClick = {}
            )
        }

        composable(SETTINGS_PRIVACY_SCREEN) {
            SettingsPrivacyScreen(
                onBackClick = { navController.popBackStack() },
                categories = FakeSettingsDataProvider.getSettingsPrivacy(),
                onItemClick = {}
            )
        }

        composable(SETTINGS_SECURITY_SCREEN) {
            SettingsSecurityScreen(
                onBackClick = { navController.popBackStack() },
                categories = FakeSettingsDataProvider.getSettingsSecurity(),
                onItemClick = {}
            )
        }

        composable(SETTINGS_FAQ_SCREEN) {
            SettingsFaqScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
