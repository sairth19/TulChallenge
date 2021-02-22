package com.tul.carts.cart.infrastructure.service.port

import com.tul.carts.cart.domain.exception.NotFoundException
import com.tul.carts.cart.domain.model.Cart
import com.tul.carts.cart.domain.service.api.CartRepository
import com.tul.carts.cart.infrastructure.data.entity.CartEntity
import com.tul.carts.cart.infrastructure.data.repository.CartJpaRepository
import com.tul.carts.cart.infrastructure.data.mapper.CartMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartRepositoryPort(private val cartRepository: CartJpaRepository) : CartRepository{
    override fun userHasPendingCart(user: String) = cartRepository.existsByUserAndStatus(user, "PE")

    override fun findCart(user: String) = cartRepository.findByUserAndStatus(user, "PE")
        .map { CartMapper.toModel(it)}.first()

    override fun findCart(uuid: UUID) = CartMapper.toModel(
            cartRepository.findById(uuid)
                .orElseThrow { NotFoundException("Cart with uuid: '${uuid} does not exist'") })

    override fun createCart(user: String): Cart {
        val createdCart = cartRepository.save(CartEntity(UUID.randomUUID(), user, "PE", listOf()))
        return CartMapper.toModel(createdCart)
    }

    override fun updateCart(cart: Cart): Cart {
        val updatedCart = cartRepository.save(CartMapper.toEntity(cart))
        return CartMapper.toModel(updatedCart)
    }
}
