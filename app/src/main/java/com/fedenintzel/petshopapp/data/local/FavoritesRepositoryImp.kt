package com.fedenintzel.petshopapp.data.local

import com.fedenintzel.petshopapp.data.mapper.toDomain
import com.fedenintzel.petshopapp.data.mapper.toEntity
import com.fedenintzel.petshopapp.domain.model.Product
import com.fedenintzel.petshopapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteProductDao
) : FavoritesRepository {

    override suspend fun toggleFavorite(product: Product) {
        val entity = product.toEntity()
        if (dao.getById(product.id) != null) {
            dao.delete(entity)
        } else {
            dao.insert(entity)
        }
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return dao.getById(id) != null
    }

    override fun getAllFavorites(): Flow<List<Product>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }
}
