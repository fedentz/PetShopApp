package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.local.CartDao
import com.fedenintzel.petshopapp.domain.model.CartItem
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import com.fedenintzel.petshopapp.data.mapper.toEntity
import com.fedenintzel.petshopapp.data.mapper.toCartItem
import com.fedenintzel.petshopapp.data.remote.dto.CartDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**

 */

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val firestore: FirebaseFirestore
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

    override suspend fun getCartsByUser(userId: String): List<CartDto> {
        return firestore.collection("carritos")
            .whereEqualTo("uid", userId)
            //.orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(CartDto::class.java) }
    }
}
