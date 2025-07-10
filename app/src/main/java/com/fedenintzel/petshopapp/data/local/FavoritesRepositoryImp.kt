package com.fedenintzel.petshopapp.data.local

import com.fedenintzel.petshopapp.data.mapper.toDomain
import com.fedenintzel.petshopapp.data.mapper.toEntity
import com.fedenintzel.petshopapp.domain.model.Product
import com.fedenintzel.petshopapp.domain.repository.FavoritesRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteProductDao,
    private val auth: FirebaseAuth
) : FavoritesRepository {

    override suspend fun toggleFavorite(product: Product) {
        val uid = auth.currentUser?.uid ?: return
        val entity = product.toEntity(uid)
        if (dao.getById(product.id, uid) != null) {
            dao.delete(entity)
        } else {
            dao.insert(entity)
        }
    }

    override suspend fun isFavorite(id: Int): Boolean {
        val uid = auth.currentUser?.uid ?: return false
        return dao.getById(id, uid) != null
    }

    override fun getAllFavorites(): Flow<List<Product>> {
        val uid = auth.currentUser?.uid.orEmpty()
        return dao.getAll(uid).map { list -> list.map { it.toDomain() } }
    }
}
