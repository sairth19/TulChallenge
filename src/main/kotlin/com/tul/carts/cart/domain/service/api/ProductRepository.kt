package com.tul.carts.cart.domain.service.api

import com.tul.carts.cart.domain.model.Product

interface ProductRepository {
    fun createProduct(product: Product): Product
    fun findProduct(name: String): List<Product>
    fun allProducts(): List<Product>
    fun existSku(skuCode: String): Boolean
}
