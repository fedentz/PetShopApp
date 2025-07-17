package com.fedenintzel.petshopapp.domain.usecase

import com.fedenintzel.petshopapp.data.local.CartDao
import com.fedenintzel.petshopapp.data.local.CartItemEntity
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.model.CartItem
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * UseCase para obtener el carrito guardado hasta el momento, desde Room.
 *
 */

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(): List<CartItem> {
        val uid = auth.currentUser?.uid ?: return emptyList()
        return cartRepository.getCartItems(uid)
    }
}