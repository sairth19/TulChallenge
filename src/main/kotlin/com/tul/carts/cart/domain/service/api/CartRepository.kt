package com.tul.carts.cart.domain.service.api

import com.tul.carts.cart.domain.model.Cart
import java.util.*

interface CartRepository {
    fun userHasPendingCart(user: String): Boolean
    fun findCart(user: String): Cart
    fun findCart(uuid: UUID): Cart
    fun createCart(user: String): Cart
    fun updateCart(cart: Cart): Cart
}
