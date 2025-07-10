package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.model.CartItem
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(
    private val repository: CartRepository,
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(item: CartItem) {
        val uid = auth.currentUser?.uid ?: return
        repository.addOrUpdateCartItem(uid, item)
    }
}