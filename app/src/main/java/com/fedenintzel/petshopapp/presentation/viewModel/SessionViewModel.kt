package com.fedenintzel.petshopapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor() : ViewModel() {

    var isLoggedIn = mutableStateOf<Boolean?>(null)
        private set

    var currentUser = mutableStateOf<FirebaseUser?>(null)
        private set


    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val user = FirebaseAuth.getInstance().currentUser
            currentUser.value = user
            isLoggedIn.value = user != null
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        currentUser.value = null
        isLoggedIn.value = false
    }
}
