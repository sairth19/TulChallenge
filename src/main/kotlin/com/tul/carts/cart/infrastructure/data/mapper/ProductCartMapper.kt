package com.tul.carts.cart.infrastructure.data.mapper

import com.tul.carts.cart.domain.model.ProductCart
import com.tul.carts.cart.infrastructure.data.entity.CartEntity
import com.tul.carts.cart.infrastructure.data.entity.ProductCartEntity
import com.tul.carts.cart.infrastructure.data.entity.ProductCartId
import com.tul.carts.cart.infrastructure.data.entity.ProductEntity
import java.util.*

object ProductCartMapper {
    fun toEntity(product: ProductCart, cartUUID: UUID) = ProductCartEntity(
        ProductCartId(product.productUUID, cartUUID),
        ProductEntity(product.productUUID, "", "", ""),
        CartEntity(cartUUID, "", "", listOf()),
        product.quantity
    )

    fun toModel(product: ProductCartEntity) =
        ProductCart(product.product.uuid, product.product.name, product.quantity)
}
