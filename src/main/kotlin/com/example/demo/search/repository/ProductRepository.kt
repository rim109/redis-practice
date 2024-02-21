package com.example.demo.search.repository

import com.example.demo.search.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>{

}
