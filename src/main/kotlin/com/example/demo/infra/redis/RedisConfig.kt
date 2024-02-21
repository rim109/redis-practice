package com.example.demo.infra.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    @Value("\${spring.redis.port}")
    private val port: Int,

    @Value("\${spring.redis.host}")
    private val host: String

) {
        @Bean
        fun redisConnectionFactory(): RedisConnectionFactory {
            return LettuceConnectionFactory(host, port)
        }

        @Bean
        fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, String> {
            val redisTemplate = RedisTemplate<String, String>()
            redisTemplate.connectionFactory = redisConnectionFactory
            redisTemplate.keySerializer = StringRedisSerializer()
            redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
            return redisTemplate
        }

//        @Bean
//        fun redisCacheManager(redisConnectionFactory: RedisConnectionFactory): RedisCacheManager {
//            val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                    GenericJackson2JsonRedisSerializer()
//                ))
//
//            return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(redisConnectionFactory)
//                .cacheDefaults(redisCacheConfiguration)
//                .build()
//        }

}