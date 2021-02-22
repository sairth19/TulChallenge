package com.tul.carts.cart.infrastructure.service

import com.tul.carts.cart.domain.service.ProductService
import com.tul.carts.cart.domain.service.api.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductAdapter(productRepository: ProductRepository) :
    ProductService(productRepository)
