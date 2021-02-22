package com.tul.carts.cart.domain.service

import com.tul.carts.cart.domain.exception.InvalidCartStatus
import com.tul.carts.cart.domain.exception.SecurityException
import com.tul.carts.cart.domain.model.Cart
import com.tul.carts.cart.domain.service.api.CartRepository
import com.tul.carts.cart.domain.model.ProductCart
import java.util.*

open class CartService(private val cartRepository: CartRepository) {

    fun getUserCart(user: String): Cart =
        if (cartRepository.userHasPendingCart(user))
            cartRepository.findCart(user)
        else cartRepository.createCart(user)

    fun addProduct(user: String, cartUUID: UUID, product: ProductCart): Cart {
        val cart = cartRepository.findCart(cartUUID)
        if (cart.user == user){
            cart.addProduct(product)
            return cartRepository.updateCart(cart)
        }

        throw SecurityException("Only owner user can add products to cart")
    }

    fun removeProduct(user: String, productUUID: UUID): Cart {
        if (cartRepository.userHasPendingCart(user)){
            val cart = cartRepository.findCart(user)
            cart.removeProduct(productUUID)
            return cartRepository.updateCart(cart)
        }

        throw InvalidCartStatus("This user does not have cart to checkout")
    }

    fun checkoutCart(user: String){
        if (cartRepository.userHasPendingCart(user)){
            val cart = cartRepository.findCart(user)
            cart.checkout()
            cartRepository.updateCart(cart)
        } else {
            throw InvalidCartStatus("This user does not have cart to checkout")
        }
    }
}
