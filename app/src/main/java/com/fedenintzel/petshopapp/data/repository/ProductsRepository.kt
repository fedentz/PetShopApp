package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.data.model.ProductsResponse
import com.fedenintzel.petshopapp.data.remote.RetrofitInstance

class ProductsRepository {
    suspend fun getAllProducts(): ProductsResponse {
        return RetrofitInstance.api.getProducts()
    }
}