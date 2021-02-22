package com.tul.carts.cart.infrastructure.data.entity

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "carts")
data class CartEntity(
    @Id
    @Column(name = "uuid")
    val uuid: UUID,

    @Column(name = "user")
    val user: String,

    @Column(name = "status", length = 2, columnDefinition = "char(2)")
    val status: String,

    @OneToMany(mappedBy = "cart", orphanRemoval = true, cascade = [CascadeType.ALL])
    val productEntities: List<ProductCartEntity>
)
