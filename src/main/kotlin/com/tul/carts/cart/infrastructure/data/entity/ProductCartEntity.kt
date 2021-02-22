package com.tul.carts.cart.infrastructure.data.entity

import javax.persistence.*

@Entity
@Table(name = "product_cars")
data class ProductCartEntity(
    @EmbeddedId
    val productCartId: ProductCartId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    val product: ProductEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    val cart: CartEntity,

    @Column(name = "quantity")
    val quantity: Int
)
