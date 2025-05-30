package com.fedenintzel.petshopapp.domain.model

/**
 * Modelo de dominio que representa un ítem dentro del carrito.
 *
 * Incluye datos del producto, su cantidad, precio, descuentos, etc.
 */

data class CartItem(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountedTotal: Double,
    val discountPercentage: Double,
    val thumbnail: String
)