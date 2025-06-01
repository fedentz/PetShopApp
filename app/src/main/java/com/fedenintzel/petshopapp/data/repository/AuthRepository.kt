package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.remote.AuthApiService
import com.fedenintzel.petshopapp.data.remote.dto.ForgotPasswordRequest
import com.fedenintzel.petshopapp.data.remote.dto.ForgotPasswordResponse
import com.fedenintzel.petshopapp.data.remote.dto.LoginRequest
import com.fedenintzel.petshopapp.data.remote.dto.LoginResponse
import com.fedenintzel.petshopapp.data.remote.dto.RegisterRequest
import com.fedenintzel.petshopapp.data.remote.dto.RegisterResponse
import com.fedenintzel.petshopapp.data.remote.dto.ResetPasswordRequest
import com.fedenintzel.petshopapp.data.remote.dto.ResetPasswordResponse
import com.fedenintzel.petshopapp.domain.usecase.Result
import javax.inject.Inject

/**
 * Encapsula las llamadas a AuthApiService (login y register).
 */
class AuthRepository @Inject constructor(
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

    suspend fun forgotPassword(email: String): Result<ForgotPasswordResponse> {
        return try {
            val respuesta = api.forgotPassword(ForgotPasswordRequest(email = email))
            if (respuesta.success) {
                Result.Success(respuesta)
            } else {
                Result.Error(respuesta.message ?: "Error desconocido")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error de red")
        }
    }

    suspend fun resetPassword(email: String, newPassword: String): Result<ResetPasswordResponse> {
        return try {
            val respuesta = api.resetPassword(ResetPasswordRequest(email = email, newPassword = newPassword))
            if (respuesta.success) {
                Result.Success(respuesta)
            } else {
                Result.Error(respuesta.message ?: "No se pudo restablecer contraseña")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error de red")
        }
    }


}
