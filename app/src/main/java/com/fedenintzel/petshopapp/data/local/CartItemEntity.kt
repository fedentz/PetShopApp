package com.fedenintzel.petshopapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val productId: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val thumbnail: String,

)