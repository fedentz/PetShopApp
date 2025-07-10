package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SaveCartUseCase @Inject constructor(
    private val repository: CartRepository,
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(cart: Cart) {
        val uid = auth.currentUser?.uid ?: return
        repository.clearCart(uid)
        cart.products.forEach { item ->
            repository.addOrUpdateCartItem(uid, item)
        }
    }
}