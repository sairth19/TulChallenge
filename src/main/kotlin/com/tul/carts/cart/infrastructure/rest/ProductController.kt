package com.tul.carts.cart.infrastructure.rest

import com.tul.carts.cart.domain.model.Product
import com.tul.carts.cart.domain.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("")
    fun getAllProducts(@RequestParam("name") name: String?): List<Product> {
        return this.productService.getProducts(name)
    }

    @PostMapping("")
    fun createProduct(@RequestBody product: Product) =
        this.productService.createProduct(product)
}
