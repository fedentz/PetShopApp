package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.remote.dto.ProductDto
import com.fedenintzel.petshopapp.domain.model.Product

fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    category = category,
    thumbnail = thumbnail,
    stock = stock,
    quantity = 1 // valor mockeado por ahora
)
