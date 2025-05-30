package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.mapper.toDomain
import com.fedenintzel.petshopapp.data.remote.CartRemoteDataSource
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.repository.CartRepository
import javax.inject.Inject

/**
 * Implementación de CartRepository que obtiene los datos desde la API usando Retrofit.
 *
 * Se encarga de mapear los DTO a modelos de dominio.
 */

class CartRepositoryImpl @Inject constructor(
    private val remoteDataSource: CartRemoteDataSource
) : CartRepository {
    override suspend fun getCartById(id: Int): Cart {
        return remoteDataSource.getCartById(id).toDomain()
    }
}