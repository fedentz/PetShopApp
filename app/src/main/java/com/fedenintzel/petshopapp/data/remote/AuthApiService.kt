package com.fedenintzel.petshopapp.data.remote

import com.fedenintzel.petshopapp.data.remote.dto.LoginRequest
import com.fedenintzel.petshopapp.data.remote.dto.LoginResponse
import com.fedenintzel.petshopapp.data.remote.dto.RegisterRequest
import com.fedenintzel.petshopapp.data.remote.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse
}
