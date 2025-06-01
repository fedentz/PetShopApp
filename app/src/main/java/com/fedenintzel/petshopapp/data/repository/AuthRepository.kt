package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.remote.dto.LoginRequest
import com.fedenintzel.petshopapp.data.remote.dto.LoginResponse
import com.fedenintzel.petshopapp.data.remote.dto.RegisterRequest
import com.fedenintzel.petshopapp.data.remote.dto.RegisterResponse

/**
 * Encapsula las llamadas a AuthApiService (login y register).
 */
class AuthRepository(
    private val api: AuthApiService
) {
    suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username = username, password = password)
        return api.login(request)
    }

    suspend fun register(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): RegisterResponse {
        val request = RegisterRequest(
            firstName = firstName,
            lastName = lastName,
            username = username,
            email = email,
            password = password
        )
        return api.register(request)
    }
}
