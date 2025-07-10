package com.fedenintzel.petshopapp.domain.repository

import com.fedenintzel.petshopapp.domain.model.CartItem

interface CartRepository {
    suspend fun getCartItems(userId: String): List<CartItem>
    suspend fun addOrUpdateCartItem(userId: String, item: CartItem)
    suspend fun removeCartItem(userId: String, productId: Int)
    suspend fun clearCart(userId: String)
}
