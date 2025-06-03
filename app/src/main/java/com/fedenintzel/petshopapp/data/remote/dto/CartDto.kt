package com.fedenintzel.petshopapp.data.remote.dto

/**
 * DTO (Data Transfer Object) que representa la estructura de datos del carrito
 * tal como es recibida desde la API externa.
 *
 * Este objeto se utiliza exclusivamente en la capa de red y se transforma
 * en un modelo de dominio mediante un mapper.
 */

data class CartDto(
    val id: Int,
    val products: List<CartItemDto>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)
