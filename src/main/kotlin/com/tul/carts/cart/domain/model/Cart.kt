package com.tul.carts.cart.domain.model

import com.tul.carts.cart.domain.exception.InvalidCartStatus
import java.util.*

data class Cart(val uuid: UUID = UUID.randomUUID(), val user: String, var products: List<ProductCart>, var status: String) {

    fun addProduct(product: ProductCart) {
        if (!canBeUpdated())
            throw InvalidCartStatus("This cart cannot be updated")

        this.products =
            this.products.filter { it.productUUID != product.productUUID }.union(
                listOf(product)
            ).filter { it.quantity > 0 }
    }

    fun removeProduct(productUUID: UUID) {
        if (!canBeUpdated())
            throw InvalidCartStatus("This cart cannot be updated")

        this.products =
            this.products.filter { it.productUUID != productUUID }
    }

    fun checkout() {
        if (!canBeUpdated() or products.isEmpty())
            throw InvalidCartStatus("This cart is not available to checkout")
        this.status = "CO"
    }

    private fun canBeUpdated() = this.status == "PE"
}
