package com.tul.carts.cart.infrastructure.data.repository

import com.tul.carts.cart.infrastructure.data.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CartJpaRepository : JpaRepository<CartEntity, UUID> {
    fun existsByUserAndStatus(user: String, status: String): Boolean
    fun findByUserAndStatus(user: String, status: String): List<CartEntity>
}
