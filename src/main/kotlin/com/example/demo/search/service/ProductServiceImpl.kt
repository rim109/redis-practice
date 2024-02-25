package com.example.demo.search.service

import com.example.demo.infra.redis.RedisService
import com.example.demo.search.dto.request.SearchProductRequest
import com.example.demo.search.dto.response.RankSearchProductResponse
import com.example.demo.search.dto.response.SearchResponse
import com.example.demo.search.model.Product
import com.example.demo.search.repository.ProductRepository
import com.example.demo.search.repository.SearchWordRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val searchWordRepository: SearchWordRepository,
    private val redisService: RedisService
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

    //value = 캐시 이름
    //searchWord = 캐시 내부 필드 키 이름
    //캐시에 저장되어야 하는 값 = Page 객체

    @Cacheable(value = ["name"], key = "#searchWord", cacheManager = "redisCacheManager")
    override fun searchProductList(searchWord: String, pageable: Pageable): Page<SearchResponse> {
        redisService.test()
        return searchWordRepository.getSearchProductBySearchWord(searchWord, pageable)
    }

    override fun searchRankProductList(): List<RankSearchProductResponse> {
        TODO("Not yet implemented")
    }
}