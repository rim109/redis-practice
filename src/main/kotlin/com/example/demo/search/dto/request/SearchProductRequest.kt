package com.example.demo.search.dto.request

data class SearchProductRequest(
    val name: String,
    val content: String,
    val price: Int
)
