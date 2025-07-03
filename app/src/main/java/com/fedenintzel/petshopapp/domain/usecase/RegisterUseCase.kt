package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.model.User
import com.fedenintzel.petshopapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        fullName: String,
        email: String,
        password: String
    ): User {
        val (firstName, lastName) = extractNames(fullName)
        val username = email.substringBefore("@")

        return repository.register(
            fullName = fullName,
            username = username,
            email = email,
            password = password
        )
    }

    private fun extractNames(fullName: String): Pair<String, String> {
        val parts = fullName.trim().split("\\s+".toRegex(), limit = 2)
        val firstName = parts.getOrNull(0) ?: "User"
        val lastName = parts.getOrNull(1) ?: "Registered"
        return Pair(firstName, lastName)
    }
}
