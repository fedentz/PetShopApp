package com.fedenintzel.petshopapp.presentation.screen.products

import com.fedenintzel.petshopapp.domain.model.Product

/**
 * Estado UI para la pantalla de productos.
 */

data class ProductState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)
