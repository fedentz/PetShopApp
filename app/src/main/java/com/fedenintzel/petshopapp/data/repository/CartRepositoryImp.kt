package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.mapper.toDomain
import com.fedenintzel.petshopapp.data.remote.api.CartApiService
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.repository.CartRepository

/**
 * Implementación de CartRepository que obtiene los datos desde la API usando Retrofit.
 *
 * Se encarga de mapear los DTO a modelos de dominio.
 */

class CartRepositoryImpl(
    private val api: CartApiService
) : CartRepository {

    override suspend fun getCartById(id: Int): Cart {
        return api.getCartById(id).toDomain()
    }
}
