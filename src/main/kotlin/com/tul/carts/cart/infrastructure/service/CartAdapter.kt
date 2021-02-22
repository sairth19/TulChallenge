package com.tul.carts.cart.infrastructure.service

import com.tul.carts.cart.domain.service.CartService
import com.tul.carts.cart.domain.service.api.CartRepository
import org.springframework.stereotype.Service

@Service
class CartAdapter(cartRepository: CartRepository) : CartService(cartRepository) {
}
