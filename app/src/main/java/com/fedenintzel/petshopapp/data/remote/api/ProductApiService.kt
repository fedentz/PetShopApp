package com.fedenintzel.petshopapp.data.remote.api

import com.fedenintzel.petshopapp.data.remote.dto.ProductDto
import com.fedenintzel.petshopapp.data.remote.dto.ProductsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz de Retrofit para acceder a los endpoints de productos.
 */
interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): ProductsResponseDto

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}
