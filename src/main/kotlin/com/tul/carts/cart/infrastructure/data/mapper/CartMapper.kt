package com.tul.carts.cart.infrastructure.data.mapper

import com.tul.carts.cart.domain.model.Cart
import com.tul.carts.cart.infrastructure.data.entity.CartEntity

object CartMapper {
    fun toEntity(cart: Cart) = CartEntity(
        cart.uuid,
        cart.user,
        cart.status,
        cart.products.map { ProductCartMapper.toEntity(it, cart.uuid) })

    fun toModel(cart: CartEntity) = Cart(
        cart.uuid,
        cart.user,
        cart.productEntities.map { ProductCartMapper.toModel(it) },
        cart.status
    )
}
