package com.fedenintzel.petshopapp.data.remote

import com.fedenintzel.petshopapp.data.remote.api.AuthApiService
import com.fedenintzel.petshopapp.data.remote.dto.LoginRequest
import com.fedenintzel.petshopapp.data.remote.dto.RegisterRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthApiService
) {
    suspend fun login(username: String, password: String) =
        api.login(LoginRequest(username, password))

    suspend fun register(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ) = api.register(RegisterRequest(firstName, lastName, username, email, password))


}