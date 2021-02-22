package com.tul.carts.cart.domain.service

import com.tul.carts.cart.domain.model.Product
import com.tul.carts.cart.domain.service.api.ProductRepository
import java.lang.Exception


open class ProductService(private val repository: ProductRepository) {

    fun getProducts(name: String?): List<Product> =
        if (name == null) getAllProducts() else findByName(name)

    private fun getAllProducts(): List<Product> = repository.allProducts()

    private fun findByName(name: String) = repository.findProduct(name)


    fun createProduct(product: Product): Product {
        if (repository.existSku(product.sku))
            throw Exception("Already exists a product with SKU: ${product.sku}")
        return repository.createProduct(product)
    }
}
