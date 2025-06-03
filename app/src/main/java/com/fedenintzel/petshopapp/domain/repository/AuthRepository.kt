package com.fedenintzel.petshopapp.domain.repository

import com.fedenintzel.petshopapp.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): User

    suspend fun register(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): User
}