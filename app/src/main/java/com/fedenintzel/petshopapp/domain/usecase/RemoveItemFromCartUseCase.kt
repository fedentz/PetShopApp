package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RemoveItemFromCartUseCase @Inject constructor(
    private val repository: CartRepository,
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(productId: Int) {
        val uid = auth.currentUser?.uid ?: return
        repository.removeCartItem(uid, productId)
    }
}