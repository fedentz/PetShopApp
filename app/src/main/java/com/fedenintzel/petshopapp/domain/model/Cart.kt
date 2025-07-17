package com.fedenintzel.petshopapp.domain.model

import com.google.firebase.Timestamp
/**
 * Modelo de dominio que representa un carrito de compras.
 *
 * Este modelo es independiente de la fuente de datos (API, base de datos, etc.)
 * y se utiliza en la capa de lógica de negocio y UI.
 */

data class Cart(
    val id: Int,
    val products: List<CartItem>,
    val total: Double,
    val discountedTotal: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: String,
    val timestamp: Timestamp? = null
)