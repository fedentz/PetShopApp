package com.fedenintzel.petshopapp.data.mapper

import com.fedenintzel.petshopapp.domain.model.CartItem
import com.fedenintzel.petshopapp.domain.model.Product

fun Product.toCartItem(quantity: Int = 1): CartItem {
    return CartItem(
        id = this.id,
        title = this.title,
        price = this.price * quantity,
        quantity = quantity,
        thumbnail = this.thumbnail,
        total = this.price * quantity,
        discountPercentage = 0.0,
        discountedTotal = 0.0

    )
}