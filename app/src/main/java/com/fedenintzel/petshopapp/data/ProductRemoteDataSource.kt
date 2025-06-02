package com.fedenintzel.petshopapp.data

import com.fedenintzel.petshopapp.data.remote.api.ProductApiService
import com.fedenintzel.petshopapp.data.remote.dto.ProductDto
import javax.inject.Inject


class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApiService
) {
    suspend fun getAllProducts(): List<ProductDto> = api.getAllProducts().products

    suspend fun getProductById(id: Int): ProductDto {
        return api.getProductById(id)
    }
}