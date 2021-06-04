package com.francisco.domain

interface ProductListRepository {
    suspend fun getDefaultProducts(): List<ProductDomain>
    suspend fun getProductsByName(query: String): List<ProductDomain>
}