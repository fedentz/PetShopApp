package com.fedenintzel.petshopapp.domain.repository

import com.fedenintzel.petshopapp.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): User

    suspend fun register(
        fullName: String,
        username: String,
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): User

    suspend fun getCurrentUser(): User
}