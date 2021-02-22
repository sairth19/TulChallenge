package com.tul.carts.cart.domain.model

import com.tul.carts.cart.domain.exception.InvalidCartStatus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class CartTest {

    @Test
    fun addProduct(){
        val productCart = ProductCart(UUID.randomUUID(), "Product test", 1)
        val cart = Cart(UUID.randomUUID(), "sairth19", listOf(), "PE")

        cart.addProduct(productCart)
        Assertions.assertEquals(1, cart.products.size)
    }

    @Test
    fun doesNotAddProductWhenItsQuantityIsEqualOrLowerThanZero(){
        val productCart = ProductCart(UUID.randomUUID(), "Product test", 0)
        val cart = Cart(UUID.randomUUID(), "sairth19", listOf(), "PE")

        cart.addProduct(productCart)

        Assertions.assertTrue(cart.products.isEmpty())
    }

    @Test
    fun updateProductWhenCartAlreadyContainsIt(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val newProductOrder = ProductCart(oldProductOrder.productUUID, "Product test", 3)
        val cart = Cart(UUID.randomUUID(), "sairth19", listOf(oldProductOrder), "PE")

        cart.addProduct(newProductOrder)

        Assertions.assertEquals(1, cart.products.size)
        Assertions.assertEquals(3, cart.products[0].quantity)
    }

    @Test
    fun updateProductWhenCartAlreadyContainsItAndManyOthers(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val otherProductCart = ProductCart(UUID.randomUUID(), "Product test 2", 1)
        val anotherProductCart = ProductCart(UUID.randomUUID(), "Product test 3", 1)

        val newProductOrder = ProductCart(oldProductOrder.productUUID, "Product test", 3)
        val cart = Cart(
            UUID.randomUUID(),
            "sairth19",
            listOf(oldProductOrder, otherProductCart, anotherProductCart),
            "PE"
        )

        cart.addProduct(newProductOrder)

        val updatedProducts = cart.products.filter { it.productUUID ==  oldProductOrder.productUUID}

        Assertions.assertEquals(3, cart.products.size)
        Assertions.assertEquals(1, updatedProducts.size)
        Assertions.assertEquals( 3,updatedProducts.first().quantity)
    }

    @Test
    fun shouldThrowsInvalidCartStatusWhenIsCompletedAndTriesToAddProduct(){
        val oldProductCart = ProductCart(UUID.randomUUID(), "Product test", 1)
        val productCart = ProductCart(UUID.randomUUID(), "Product test 2", 1)
        val cart = Cart(UUID.randomUUID(), "sairth19", listOf(oldProductCart), "CO")

        assertThrows<InvalidCartStatus> { cart.addProduct(productCart) }
    }

    @Test
    fun removeProduct(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val cart = Cart(UUID.randomUUID(), "sairth19", listOf(oldProductOrder), "PE")

        cart.removeProduct(oldProductOrder.productUUID)

        Assertions.assertTrue(cart.products.isEmpty())
    }

    @Test
    fun removeProductWhenCartHasManyProducts(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val otherProductCart = ProductCart(UUID.randomUUID(), "Product test 2", 1)
        val anotherProductCart = ProductCart(UUID.randomUUID(), "Product test 3", 1)

        val cart = Cart(
            UUID.randomUUID(),
            "sairth19",
            listOf(oldProductOrder, otherProductCart, anotherProductCart),
            "PE"
        )

        cart.removeProduct(oldProductOrder.productUUID)

        Assertions.assertEquals(2, cart.products.size)
    }

    @Test
    fun doesNothingWhenTriesToRemoveProductAndCartDoesNotContainIt(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val otherProductCart = ProductCart(UUID.randomUUID(), "Product test 2", 1)
        val anotherProductCart = ProductCart(UUID.randomUUID(), "Product test 3", 1)

        val cart = Cart(
            UUID.randomUUID(),
            "sairth19",
            listOf(oldProductOrder, otherProductCart, anotherProductCart),
            "PE"
        )

        cart.removeProduct(UUID.randomUUID())

        Assertions.assertEquals(3, cart.products.size)
    }

    @Test
    fun shouldThrowsInvalidCartStatusWhenIsCompletedAndTriesToRemoveProduct(){
        val oldProductCart = ProductCart(UUID.randomUUID(), "Product test", 1)
        val cart = Cart(UUID.randomUUID(), "sairth19", listOf(oldProductCart), "CO")

        assertThrows<InvalidCartStatus> { cart.removeProduct(oldProductCart.productUUID) }
    }

    @Test
    fun checkoutTest(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val otherProductCart = ProductCart(UUID.randomUUID(), "Product test 2", 1)
        val anotherProductCart = ProductCart(UUID.randomUUID(), "Product test 3", 1)

        val cart = Cart(
            UUID.randomUUID(),
            "sairth19",
            listOf(oldProductOrder, otherProductCart, anotherProductCart),
            "PE"
        )

        cart.checkout()

        Assertions.assertEquals("CO", cart.status)
    }


    @Test
    fun shouldThrowsInvalidCartStatusWhenCartIsEmptyAndTriesToCheckoutIt(){
        val cart = Cart(UUID.randomUUID(),"sairth19", listOf(), "PE")
        assertThrows<InvalidCartStatus> { cart.checkout() }
    }

    @Test
    fun shouldThrowsInvalidCartStatusWhenCartIsCompletedAndTriesToCheckoutIt(){
        val oldProductOrder = ProductCart(UUID.randomUUID(), "Product test", 1)
        val otherProductCart = ProductCart(UUID.randomUUID(), "Product test 2", 1)
        val anotherProductCart = ProductCart(UUID.randomUUID(), "Product test 3", 1)

        val cart = Cart(
            UUID.randomUUID(),
            "sairth19",
            listOf(oldProductOrder, otherProductCart, anotherProductCart),
            "CO"
        )
        assertThrows<InvalidCartStatus> { cart.checkout() }
    }
}
