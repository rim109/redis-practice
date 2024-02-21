package com.example.demo.search.repository

import com.example.demo.infra.queryDsL.QueryDslSupport
import com.example.demo.search.dto.response.SearchResponse
import com.example.demo.search.model.Product
import com.example.demo.search.model.QProduct
import com.example.demo.search.model.QSearchWord
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class SearchWordRepositoryImpl : SearchWordRepository, QueryDslSupport() {
    private val searchWord = QSearchWord.searchWord
    private val product = QProduct.product
    override fun getSearchProductBySearchWord(
        searchWord: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        val whereClause = BooleanBuilder()
        whereClause.and(product.name.contains(searchWord))

        val totalCount = queryFactory.select(product.count()).from(product).where(whereClause).fetchOne() ?: 0L

        val query = queryFactory
            .select(
                Projections.constructor(
                    SearchResponse::class.java,
                    product.id,
                    product.name
                )
            )
            .from(product)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(product.name.asc(), product.id.asc())
            .fetch()

        return PageImpl(query, pageable,totalCount)
    }

    //containsIgnoreCase는 대소문자를 구분하지 않고 입력 문자열에 지정된 하위 문자열이 포함되어 있는지 테스트하는 것
    override fun searchProductListByName(name: String): List<Product> {
        return queryFactory.selectFrom(product).where(product.name.containsIgnoreCase(name)).fetch()
    }


}