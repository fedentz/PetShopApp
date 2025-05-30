package com.fedenintzel.petshopapp.data.remote

import com.fedenintzel.petshopapp.data.remote.api.CartApiService
import com.fedenintzel.petshopapp.data.remote.dto.CartDto
import javax.inject.Inject

class CartRemoteDataSource @Inject constructor(
    private val api: CartApiService
) {
    suspend fun getCartById(id: Int): CartDto {
        return api.getCartById(id)
    }
}