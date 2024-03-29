package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class RedisPracticeApplication

fun main(args: Array<String>) {
	runApplication<RedisPracticeApplication>(*args)
}
