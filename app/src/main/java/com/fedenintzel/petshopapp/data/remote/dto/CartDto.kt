package com.fedenintzel.petshopapp.data.remote.dto

import com.google.firebase.Timestamp

/**
 * DTO (Data Transfer Object) que representa la estructura de datos del carrito
 * tal como es recibida desde la API externa.
 *
 * Este objeto se utiliza exclusivamente en la capa de red y se transforma
 * en un modelo de dominio mediante un mapper.
 */

data class CartDto(
    val id: Int = 0,
    val products: List<CartItemDto> = emptyList(),
    val total: Double = 0.0,
    val discountedTotal: Double = 0.0,
    val totalProducts: Int = 0,
    val totalQuantity: Int = 0,
    val uid: String = "",
    val timestamp: Timestamp? = null
)
