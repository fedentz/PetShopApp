package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.fedenintzel.petshopapp.data.mapper.toDomain
import javax.inject.Inject

class GetPurchaseHistoryUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(userId: String): List<Cart> {
        return repository.getCartsByUser(userId).map { it.toDomain() }
    }
}