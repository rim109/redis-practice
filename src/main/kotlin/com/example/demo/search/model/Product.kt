package com.example.demo.search.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(
    @Column(name = "name")
    val name: String,

    @Column(name = "content")
    val content: String,

    @Column(name = "price")
    val price: String,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}