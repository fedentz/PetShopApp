package com.fedenintzel.petshopapp.presentation.viewmodel

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

    fun checkSession() {
        viewModelScope.launch {
            val authUser = FirebaseAuth.getInstance().currentUser
            firebaseUser.value = authUser
            isLoggedIn.value = authUser != null

            if (authUser != null) {
                try {
                    user.value = getCurrentUserUseCase()
                } catch (e: Exception) {
                    // Si falla, igual mantenemos la sesión activa
                    user.value = null
                }
            }
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        firebaseUser.value = null
        user.value = null
        isLoggedIn.value = false
    }
}
