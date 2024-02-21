package com.example.demo.search.repository

import com.example.demo.search.dto.response.SearchResponse
import com.example.demo.search.model.Product
import com.example.demo.search.model.SearchWord
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SearchWordRepository {
    fun getSearchProductBySearchWord(searchWord: String, pageable: Pageable) : Page<SearchResponse>

    fun searchProductListByName(name: String): List<Product>
}