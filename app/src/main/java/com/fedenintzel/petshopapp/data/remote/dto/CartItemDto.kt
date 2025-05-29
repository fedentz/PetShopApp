package com.fedenintzel.petshopapp.data.remote.dto

/**
 * DTO que representa un producto dentro de un carrito,
 * tal como es recibido desde la API externa.
 */

data class CartItemDto(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountedTotal: Double,
    val discountPercentage: Double,
    val thumbnail: String
)
