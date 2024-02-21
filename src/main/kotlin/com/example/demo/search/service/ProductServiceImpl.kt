package com.example.demo.search.service

import com.example.demo.search.dto.request.SearchProductRequest
import com.example.demo.search.dto.response.RankSearchProductResponse
import com.example.demo.search.dto.response.SearchResponse
import com.example.demo.search.repository.ProductRepository
import com.example.demo.search.repository.SearchWordRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val searchWordRepository: SearchWordRepository
): ProductService {
    override fun createProduct(request: SearchProductRequest) {
        TODO("Not yet implemented")
    }

    @Cacheable(value = ["name"], key = "#searchWord", cacheManager = "redisCacheManager")
    override fun searchProductList(searchWord: String, pageable: Pageable): Page<SearchResponse> {
        return searchWordRepository.getSearchProductBySearchWord(searchWord, pageable)
    }

    override fun searchRankProductList(): List<RankSearchProductResponse> {
        TODO("Not yet implemented")
    }
}