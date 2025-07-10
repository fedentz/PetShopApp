package com.fedenintzel.petshopapp.data.local

import com.fedenintzel.petshopapp.data.mapper.toCartItem
import com.fedenintzel.petshopapp.data.mapper.toEntity
import com.fedenintzel.petshopapp.domain.model.CartItem
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override suspend fun getCartItems(userId: String): List<CartItem> {
        return cartDao.getItemsForUser(userId).map { it.toCartItem() }
    }

    override suspend fun addOrUpdateCartItem(userId: String, item: CartItem) {
        cartDao.insertItem(item.toEntity(userId))
    }

    override suspend fun removeCartItem(userId: String, productId: Int) {
        cartDao.removeItem(userId, productId)
    }

    override suspend fun clearCart(userId: String) {
        cartDao.clearCart(userId)
    }
}