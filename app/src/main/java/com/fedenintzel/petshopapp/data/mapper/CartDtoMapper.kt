package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.remote.dto.CartDto
import com.fedenintzel.petshopapp.data.remote.dto.CartItemDto
import com.fedenintzel.petshopapp.domain.model.Cart
import com.fedenintzel.petshopapp.domain.model.CartItem

/**
 * Extensión que transforma un CartDto (modelo de red) en un modelo de dominio Cart.
 */

fun CartDto.toDomain(): Cart = Cart(
    id = id,
    products = products.map { it.toDomain() },
    total = total,
    discountedTotal = discountedTotal,
    totalProducts = totalProducts,
    totalQuantity = totalQuantity,
    userId = userId
)

/**
 * Extensión que transforma un CartItemDto (modelo de red) en un modelo de dominio CartItem.
 */

fun CartItemDto.toDomain(): CartItem = CartItem(
    id = id,
    title = title,
    price = price,
    quantity = quantity,
    total = total,
    discountedTotal = discountedTotal,
    discountPercentage = discountPercentage,
    thumbnail = thumbnail
)