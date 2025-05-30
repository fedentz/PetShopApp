package com.fedenintzel.petshopapp.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

// Data class para el body de la petición
data class LoginRequest(
    val username: String,
    val password: String
)

// Data class para la respuesta de login
data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val token: String
)

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}
