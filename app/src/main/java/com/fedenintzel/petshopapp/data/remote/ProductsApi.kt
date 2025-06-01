package com.fedenintzel.petshopapp.data.remote

import com.fedenintzel.petshopapp.data.model.ProductsResponse
import com.fedenintzel.petshopapp.data.model.Product
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.Retrofit

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): ProductsResponse
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}