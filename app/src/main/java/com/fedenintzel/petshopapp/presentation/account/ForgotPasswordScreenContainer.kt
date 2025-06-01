package com.fedenintzel.petshopapp.presentation.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.fedenintzel.petshopapp.presentation.viewmodel.ForgotPasswordUiState

/**
 * Este container conecta el StateFlow<ForgotPasswordUiState> del ViewModel
 * con un State<ForgotPasswordUiState> de Compose, usando produceState().
 */
@Composable
fun ForgotPasswordScreenContainer(
    viewModel: ForgotPasswordViewModel,
    uiState: ForgotPasswordUiState,
    onSendResetLinkClick: (String) -> Unit,
    onResetPasswordClick: () -> Unit,
    onBackToLoginClick: () -> Unit
) {
    val uiState by produceState<ForgotPasswordUiState>(
        initialValue = ForgotPasswordUiState(),
        key1 = viewModel
    ) {
        viewModel.uiState
            .collect { newState ->
                value = newState
            }
    }

    ForgotPasswordScreen(
        uiState = uiState,
        onEmailChange = { viewModel.onEmailChange(it) },
        onSendClick = { viewModel.sendResetLink(email) },
        onNewPasswordChange = { viewModel.onNewPasswordChange(it) },
        onConfirmPasswordChange = { viewModel.onConfirmPasswordChange(it) },
        onResetClick = { viewModel.resetPassword() },
        onBackToLoginClick = onBackToLoginClick
    )
}
