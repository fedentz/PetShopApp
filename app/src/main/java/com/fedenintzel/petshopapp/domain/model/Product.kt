package com.fedenintzel.petshopapp.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String,
    val category: String,
    val stock: Int,
    val quantity: Int
)

