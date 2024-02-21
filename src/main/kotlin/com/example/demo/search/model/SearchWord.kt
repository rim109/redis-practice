package com.example.demo.search.model

import jakarta.persistence.*

@Entity
@Table(name="searchWord")
class SearchWord(
    @Column(name = "rank")
    val rank: Int,

    @Column(name = "topic")
    val topic: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}