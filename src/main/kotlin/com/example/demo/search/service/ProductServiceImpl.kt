package com.example.demo.search.service

import com.example.demo.search.dto.request.SearchProductRequest
import com.example.demo.search.dto.response.RankSearchProductResponse
import com.example.demo.search.dto.response.SearchResponse
import com.example.demo.search.model.Product
import com.example.demo.search.repository.ProductRepository
import com.example.demo.search.repository.SearchWordRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val searchWordRepository: SearchWordRepository
): ProductService {
    override fun createProduct(request: SearchProductRequest) {
        val product = productRepository.save(
            Product(
                name = request.name,
                content = request.content,
                price = request.price.toString()
            )
        )

    }

    @Cacheable(value = ["name"], key = "#searchWord", cacheManager = "redisCacheManager")
    override fun searchProductList(searchWord: String): Page<SearchResponse> {
        val pageable = PageRequest.of(5,3)
        return searchWordRepository.getSearchProductBySearchWord(searchWord, pageable)
    }

    override fun searchRankProductList(): List<RankSearchProductResponse> {
        TODO("Not yet implemented")
    }
}