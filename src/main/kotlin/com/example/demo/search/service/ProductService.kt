package com.example.demo.search.service

import com.example.demo.search.dto.request.SearchProductRequest
import com.example.demo.search.dto.response.RankSearchProductResponse
import com.example.demo.search.dto.response.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductService {
    fun createProduct(request: SearchProductRequest)

    fun searchProductList(searchWord: String): Page<SearchResponse>

    fun searchRankProductList(): List<RankSearchProductResponse>
}