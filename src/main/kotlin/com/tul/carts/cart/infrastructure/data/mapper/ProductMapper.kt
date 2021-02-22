package com.tul.carts.cart.infrastructure.data.mapper

import com.tul.carts.cart.domain.model.Product
import com.tul.carts.cart.infrastructure.data.entity.ProductEntity

object ProductMapper {
    fun toEntity(product: Product) =
        ProductEntity(product.uuid, product.name, product.sku, product.description)

    fun toDomain(product: ProductEntity) =
        Product(product.uuid, product.name, product.sku, product.description)
}
