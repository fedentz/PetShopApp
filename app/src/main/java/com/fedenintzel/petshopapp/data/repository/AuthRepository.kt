package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.remote.LoginRequest
import com.fedenintzel.petshopapp.data.remote.LoginResponse
import java.io.IOException


sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val message: String): Result<Nothing>()
}

class AuthRepository(
    private val api: AuthApiService
) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(username, password))
            Result.Success(response)
        } catch (io: IOException) {
            // error de red
            Result.Error("Error de red")
        } catch (e: Exception) {
            // error genérico (400, 500, parsing, etc.)
            Result.Error(e.localizedMessage ?: "Error desconocido")
        }
    }
}
