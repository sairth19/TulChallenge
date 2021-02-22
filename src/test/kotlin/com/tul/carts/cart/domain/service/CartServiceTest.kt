package com.tul.carts.cart.domain.service

import com.tul.carts.cart.domain.exception.InvalidCartStatus
import com.tul.carts.cart.domain.exception.SecurityException
import com.tul.carts.cart.domain.model.Cart
import com.tul.carts.cart.domain.model.ProductCart
import com.tul.carts.cart.domain.service.api.CartRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import java.util.*

class CartServiceTest {
    private val cartRepository: CartRepository = Mockito.mock(CartRepository::class.java)

    @Test
    fun createCartWhenUserDoesNotHaveCartPending(){
        val createdUuid = UUID.randomUUID()
        Mockito.`when`(cartRepository.userHasPendingCart(anyString())).thenReturn(false)
        Mockito.`when`(cartRepository.createCart(anyString())).thenReturn(Cart(createdUuid, "sairth19", listOf(), "PE"))

        val service = CartService(cartRepository)

        service.getUserCart("sairth19")

        Mockito.verify(cartRepository, Mockito.times(1)).userHasPendingCart(anyString())
        Mockito.verify(cartRepository, Mockito.times(0)).findCart(anyString())
        Mockito.verify(cartRepository, Mockito.times(1)).createCart(anyString())
    }

    @Test
    fun returnCartWhenUserHaveCartPending(){
        val createdUuid = UUID.randomUUID()
        Mockito.`when`(cartRepository.userHasPendingCart(anyString())).thenReturn(true)
        Mockito.`when`(cartRepository.findCart(anyString())).thenReturn(Cart(createdUuid, "sairth19", listOf(), "PE"))

        val service = CartService(cartRepository)

        service.getUserCart("sairth19")

        Mockito.verify(cartRepository, Mockito.times(1)).userHasPendingCart(anyString())
        Mockito.verify(cartRepository, Mockito.times(1)).findCart(anyString())
        Mockito.verify(cartRepository, Mockito.times(0)).createCart(anyString())
    }

    @Test
    fun throwsExceptionWhenUserIsNotOwnerAndTriesToAddProduct(){
        val cartUUID = UUID.randomUUID()
        Mockito.`when`(cartRepository.findCart(cartUUID))
            .thenReturn(Cart(cartUUID, "sairth19", listOf(), "PE"))

        val service = CartService(cartRepository)
        assertThrows<SecurityException> { service.addProduct("jhondoe", cartUUID, ProductCart(UUID.randomUUID(), "", 1)) }
    }

    @Test
    fun throwsInvalidStatusWhenCartIsCompletedAndUserTriesToAddProduct(){
        val cartUUID = UUID.randomUUID()
        Mockito.`when`(cartRepository.findCart(cartUUID))
            .thenReturn(Cart(cartUUID, "sairth19", listOf(), "CO"))

        val service = CartService(cartRepository)
        assertThrows<InvalidCartStatus> { service.addProduct("sairth19", cartUUID, ProductCart(UUID.randomUUID(), "", 1)) }
    }

    @Test
    fun throwsInvalidStatusWhenCartIsCompletedAndUserTriesToDeleteProduct(){
        val cartUUID = UUID.randomUUID()
        Mockito.`when`(cartRepository.userHasPendingCart(anyString())).thenReturn(true)
        Mockito.`when`(cartRepository.findCart(anyString()))
            .thenReturn(Cart(cartUUID, "sairth19", listOf(), "CO"))

        val service = CartService(cartRepository)
        assertThrows<InvalidCartStatus> { service.removeProduct("sairth19", UUID.randomUUID()) }
    }

    @Test
    fun throwsInvalidStatusWhenUserDoesNotHavePendingCartAndTriesToDeleteProduct(){
        Mockito.`when`(cartRepository.userHasPendingCart(anyString())).thenReturn(false)

        val service = CartService(cartRepository)
        assertThrows<InvalidCartStatus> { service.removeProduct("sairth19", UUID.randomUUID()) }
    }
}
