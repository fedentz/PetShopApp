package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.model.User
import com.fedenintzel.petshopapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): User {
        return repository.getCurrentUser()
    }
}