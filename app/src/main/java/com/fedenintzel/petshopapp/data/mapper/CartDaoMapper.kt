package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.data.local.CartItemEntity
import com.fedenintzel.petshopapp.domain.model.CartItem
import com.fedenintzel.petshopapp.domain.model.Product

// Product → CartItem
fun Product.toCartItem(): CartItem {
    return CartItem(
        id = this.id,
        title = this.title,
        price = this.price * this.quantity,
        quantity = this.quantity,
        total = this.price * this.quantity,
        discountedTotal = this.price * this.quantity,
        discountPercentage = 0.0,
        thumbnail = this.thumbnail
    )
}

// CartItem → CartItemEntity
fun CartItem.toEntity(userId: String): CartItemEntity {
    return CartItemEntity(
        userId = userId,
        productId = this.id,
        title = this.title,
        price = this.price,
        quantity = this.quantity,
        thumbnail = this.thumbnail
    )
}

// CartItemEntity → CartItem
fun CartItemEntity.toCartItem(): CartItem {
    return CartItem(
        id = this.productId,
        title = this.title,
        price = this.price,
        quantity = this.quantity,
        thumbnail = this.thumbnail,
        total = this.price,
        discountedTotal = this.price,
        discountPercentage = 0.0
    )
}

