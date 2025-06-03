package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.local.FavoriteProductEntity
import com.fedenintzel.petshopapp.domain.model.Product

fun Product.toEntity() = FavoriteProductEntity(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail,
    category = category,
    stock = stock
)

fun FavoriteProductEntity.toDomain() = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail,
    category = category,
    stock = stock,
    quantity = 1 // valor mockeado
)
