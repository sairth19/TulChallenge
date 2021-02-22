package com.tul.carts.cart.domain.model

enum class CartStatus(val value: String) {
    PENDING("PE"),
    COMPLETED("CO");

    companion object {
        fun getByValue(value: String) =
            values().firstOrNull { item -> item.value == value }
    }
}

