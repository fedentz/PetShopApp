package com.fedenintzel.petshopapp.data.remote.dto

// Para mapear la respuesta paginada de productos:

data class ProductsResponseDto(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)