package com.fedenintzel.petshopapp.data.remote.api

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

    @POST("users/add")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

}
