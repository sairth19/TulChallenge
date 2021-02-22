package com.tul.carts.cart.infrastructure.data.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Column

import javax.persistence.Embeddable


@Embeddable
class ProductCartId(
    @Column(name = "")
    val productId: UUID,

    @Column(name = "")
    val cartId: UUID) : Serializable {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ProductCartId
        return Objects.equals(productId, that.productId) &&
                Objects.equals(cartId, that.cartId)
    }

    override fun hashCode(): Int {
        return Objects.hash(productId, cartId)
    }
}
