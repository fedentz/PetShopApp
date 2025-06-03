package com.fedenintzel.petshopapp.presentation.account

import androidx.compose.runtime.Composable

@Composable
fun ForgotPasswordScreenContainer(
    onBackToLoginClick: () -> Unit
) {
    ForgotPasswordScreen(onBackToLoginClick = onBackToLoginClick)
}
