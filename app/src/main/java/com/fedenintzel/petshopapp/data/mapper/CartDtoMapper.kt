package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.remote.dto.CartDto
import com.fedenintzel.petshopapp.data.remote.dto.CartItemDto
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.model.CartItem

fun CartDto.toDomain(): Cart {
    return Cart(
        id = id,
        products = products.map { it.toDomain() },
        total = total,
        discountedTotal = discountedTotal,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity,
        userId = uid,
        timestamp = timestamp
    )
}

fun CartItemDto.toDomain(): CartItem {
    return CartItem(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedPrice,
        thumbnail = thumbnail
    )
}
