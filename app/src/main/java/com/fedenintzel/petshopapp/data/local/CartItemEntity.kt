package com.fedenintzel.petshopapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart_items",
    primaryKeys = ["userId", "productId"]
)
data class CartItemEntity(
    val userId: String,
    val productId: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val thumbnail: String
)