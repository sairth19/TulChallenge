package com.tul.carts.cart.infrastructure.data.repository

import com.tul.carts.cart.domain.model.Product
import com.tul.carts.cart.infrastructure.data.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProductJpaRepository : JpaRepository<ProductEntity, UUID> {
    fun findByName(name: String): List<ProductEntity>

    fun existsBySku(sku: String): Boolean
}
