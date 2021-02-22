package com.tul.carts.cart.domain.model

import java.util.*

data class Product(val uuid: UUID = UUID.randomUUID(), val name: String, val sku: String, val description: String )
