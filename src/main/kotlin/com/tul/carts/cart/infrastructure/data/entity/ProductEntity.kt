package com.tul.carts.cart.infrastructure.data.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @Column(name = "uuid")
    val uuid: UUID,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "sku", nullable = false)
    val sku: String,

    @Column(name = "description")
    val description: String
)


