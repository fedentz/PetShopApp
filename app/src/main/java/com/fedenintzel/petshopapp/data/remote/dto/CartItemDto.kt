package com.fedenintzel.petshopapp.data.remote.dto

/**
 * DTO que representa un producto dentro de un carrito,
 * tal como es recibido desde la API externa.
 */

data class CartItemDto(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val total: Double = 0.0,
    val discountPercentage: Double = 0.0,
    val discountedPrice: Double = 0.0,
    val thumbnail: String = ""
)
