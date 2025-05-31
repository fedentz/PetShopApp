package com.fedenintzel.petshopapp.data.remote

import com.fedenintzel.petshopapp.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.Retrofit

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): ProductsResponse
}