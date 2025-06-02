package com.fedenintzel.petshopapp.domain.repository

import com.fedenintzel.petshopapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun toggleFavorite(product: Product)
    suspend fun isFavorite(id: Int): Boolean
    fun getAllFavorites(): Flow<List<Product>>
}
