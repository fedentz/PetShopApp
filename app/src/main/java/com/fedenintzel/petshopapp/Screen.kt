package com.fedenintzel.petshopapp

sealed class Screen(val route: String) {
    object Login         : Screen("login")
    object CreateAccount : Screen("create_account")
}
