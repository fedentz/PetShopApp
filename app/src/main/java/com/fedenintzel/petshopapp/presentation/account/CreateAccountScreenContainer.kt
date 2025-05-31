package com.fedenintzel.petshopapp.presentation.account

import androidx.compose.runtime.Composable

import com.fedenintzel.petshopapp.presentation.viewmodel.CreateAccountUiState
import kotlinx.coroutines.flow.StateFlow

/**
 * Container que expone directamente createAccountViewModel.uiState (un Compose State)
 * y lo pasa a CreateAccountScreen, junto con las lambdas de acción.
 */
@Composable
fun CreateAccountScreenContainer(
    createAccountViewModel: CreateAccountViewModel,
    onCreateAccountClick: (fullName: String, email: String, password: String, agreed: Boolean) -> Unit,
    onLoginClick: () -> Unit
) {
    // 1) Leemos directamente el uiState que ya es un State<CreateAccountUiState>
    val createAccountUiState: StateFlow<CreateAccountUiState> = createAccountViewModel.uiState

    // 2) Llamamos a la pantalla composable pasando uiState y las actions
    CreateAccountScreen(
        uiState = createAccountUiState,
        onCreateAccountClick = { fullName, email, password, agreed ->
            onCreateAccountClick(fullName, email, password, agreed)
        },
        onLoginClick = {
            onLoginClick()
        }
    )
}
