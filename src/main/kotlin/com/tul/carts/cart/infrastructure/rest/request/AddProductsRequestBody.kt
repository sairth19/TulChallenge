package com.tul.carts.cart.infrastructure.rest.request

import com.tul.carts.cart.domain.model.ProductCart
import java.util.*

data class AddProductRequestBody(val cartUuid: UUID, val product: ProductCart)
