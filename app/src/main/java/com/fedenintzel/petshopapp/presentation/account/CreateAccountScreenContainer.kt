package com.fedenintzel.petshopapp.presentation.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fedenintzel.petshopapp.presentation.navigation.Destinations
import com.fedenintzel.petshopapp.presentation.viewmodel.SessionViewModel
import kotlinx.coroutines.launch

/**
 * Container que conecta la pantalla con el ViewModel.
 * Se encarga de manejar navegación tras registro exitoso.
 */
@Composable
fun CreateAccountScreenContainer(
    onLoginClick: () -> Unit,
    navController: NavController,
    viewModel: CreateAccountViewModel = hiltViewModel()

) {
    val sessionViewModel: SessionViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Mostrar snackbar y navegar si se registró con éxito
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Registration successful")
                sessionViewModel.checkSession() // Fuerza la recarga del usuario completo desde firestore
                navController.navigate(Destinations.HOME) {
                    popUpTo("register") { inclusive = true }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CreateAccountScreen(
                uiState = uiState,
                onCreateAccountClick = { fullName, email, password, agreed ->
                    viewModel.createAccount(fullName, email, password, agreed)
                },
                onLoginClick = onLoginClick
            )
        }
    }
}

