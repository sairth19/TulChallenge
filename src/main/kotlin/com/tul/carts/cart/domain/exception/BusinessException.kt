package com.tul.carts.cart.domain.exception

open class BusinessException(val code: String, message: String) : Exception(message)
