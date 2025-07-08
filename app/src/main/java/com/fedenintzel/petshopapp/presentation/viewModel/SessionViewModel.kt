package com.fedenintzel.petshopapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedenintzel.petshopapp.domain.model.User
import com.fedenintzel.petshopapp.domain.usecase.GetCurrentUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel central para el manejo de sesión de usuario.
 *
 * Expone:
 * - isLoggedIn: Booleano observable que indica si hay un usuario autenticado.
 * - firebaseUser: Usuario autenticado directamente desde FirebaseAuth.
 * - user: Perfil completo del usuario recuperado desde Firestore.
 *
 * Al iniciar la app, [checkSession] se ejecuta automáticamente para poblar los datos.
 */

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    var isLoggedIn = mutableStateOf<Boolean?>(null)
        private set

    var firebaseUser = mutableStateOf<FirebaseUser?>(null)
        private set

    var user = mutableStateOf<User?>(null)
        private set

    init {
        checkSession()
    }


    /**
     * Verifica si hay un usuario logueado en FirebaseAuth y, si lo hay,
     * recupera su información completa desde Firestore usando [GetCurrentUserUseCase].
     *
     * Este método se llama automáticamente en init, pero también puede ser llamado manualmente
     * desde otras pantallas si se requiere actualizar la sesión.
     */

    fun checkSession() {
        viewModelScope.launch {
            val authUser = FirebaseAuth.getInstance().currentUser
            firebaseUser.value = authUser
            isLoggedIn.value = authUser != null

            if (authUser != null) {
                try {
                    val loadedUser = getCurrentUserUseCase()
                    Log.d("SessionViewModel", "Usuario Firestore cargado: $loadedUser")
                    user.value = loadedUser
                } catch (e: Exception) {
                    Log.e("SessionViewModel", "Error al obtener usuario de Firestore", e)
                    user.value = null
                }
            }
        }
    }

    /**
     * Cierra sesión en FirebaseAuth y limpia el estado del ViewModel.
     */

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        firebaseUser.value = null
        user.value = null
        isLoggedIn.value = false
    }
}
