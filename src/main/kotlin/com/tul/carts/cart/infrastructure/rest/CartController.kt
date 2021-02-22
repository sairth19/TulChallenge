package com.tul.carts.cart.infrastructure.rest

import com.tul.carts.cart.domain.model.Cart
import com.tul.carts.cart.domain.model.ProductCart
import com.tul.carts.cart.domain.service.CartService
import com.tul.carts.cart.infrastructure.rest.request.AddProductRequestBody
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/carts")
class CartController(private val cartService: CartService) {

    @GetMapping("/{user}")
    fun getUserCart(@PathVariable("user") user: String): Cart{
        return cartService.getUserCart(user)
    }

    @PostMapping("/{user}")
    fun addProduct(@RequestBody requestBody: AddProductRequestBody, @PathVariable("user") user: String): Cart{
        return cartService.addProduct(user, requestBody.cartUuid, requestBody.product)
    }

    @DeleteMapping("/{user}/{product-uuid}")
    fun removeProduct(@PathVariable("user") user: String, @PathVariable("product-uuid") uuid: UUID): Cart{
        return cartService.removeProduct(user, uuid)
    }

    @PostMapping("/{user}/checkout")
    fun checkout(@PathVariable("user") user: String){
        cartService.checkoutCart(user)
    }
}
