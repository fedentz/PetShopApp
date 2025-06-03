package com.fedenintzel.petshopapp.navigation

object HomeDestinations {
    const val HOME = "home"
    const val NOTIFICATIONS = "notifications"
    const val SEARCH = "search"
    const val BEST_SELLER = "best_seller"
    const val PRODUCT_DETAIL = "product_detail"

    fun productDetailRoute(productId: Int): String = "$PRODUCT_DETAIL/$productId"
}
