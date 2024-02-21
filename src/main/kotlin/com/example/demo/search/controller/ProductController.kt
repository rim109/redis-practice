package com.example.demo.search.controller

import com.example.demo.search.dto.request.SearchProductRequest
import com.example.demo.search.dto.response.RankSearchProductResponse
import com.example.demo.search.dto.response.SearchResponse
import com.example.demo.search.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/search")
    fun searchProductList(
        @RequestParam("searchWord") searchWord: String,
        @PageableDefault(size = 10, sort = ["id"]) pageable: Pageable,
    ): ResponseEntity<Page<SearchResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProductList(searchWord, pageable))
    }
    @GetMapping("/search/rank")
    fun searchRankProductList(): ResponseEntity<List<RankSearchProductResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchRankProductList())
    }

    @PostMapping("/products")
    fun createProduct(
        @RequestBody request: SearchProductRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(request))
    }

}