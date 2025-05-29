package com.fedenintzel.petshopapp.domain.repository

import com.fedenintzel.petshopapp.domain.model.Cart

/**
 * Interfaz que define las operaciones disponibles sobre el carrito de compras.
 *
 * Separa la lógica de acceso a datos del resto de la app.
 */

interface CartRepository {

    /**
     * Obtiene un carrito por su ID desde una fuente de datos remota o local.
     *
     * @param id ID del carrito
     * @return Instancia del carrito
     */
    suspend fun getCartById(id: Int): Cart
}
