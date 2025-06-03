package com.fedenintzel.petshopapp.domain.repository

import com.fedenintzel.petshopapp.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product
}
