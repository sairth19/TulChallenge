package com.tul.carts.cart.infrastructure.service.port

import com.tul.carts.cart.domain.model.Product
import com.tul.carts.cart.domain.service.api.ProductRepository
import com.tul.carts.cart.infrastructure.data.mapper.ProductMapper
import com.tul.carts.cart.infrastructure.data.repository.ProductJpaRepository
import org.springframework.stereotype.Service

@Service
class ProductRepositoryPort(private val productJpaRepository: ProductJpaRepository): ProductRepository {
    override fun createProduct(product: Product): Product {
        val savedProduct = this.productJpaRepository.save(ProductMapper.toEntity(product))
        return ProductMapper.toDomain(savedProduct)
    }

    override fun findProduct(name: String): List<Product> =
        this.productJpaRepository.findByName(name)
            .map { ProductMapper.toDomain(it) }

    override fun allProducts(): List<Product> = this.productJpaRepository.findAll()
        .map { ProductMapper.toDomain(it) }

    override fun existSku(skuCode: String): Boolean = this.productJpaRepository.existsBySku(skuCode)
}
