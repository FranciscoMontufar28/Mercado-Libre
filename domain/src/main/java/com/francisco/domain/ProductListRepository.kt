package com.francisco.domain

interface ProductListRepository {
    suspend fun getDefaultProducts(): List<ProductDomain>
    suspend fun getProductsByName(query: String): List<ProductDomain>
    suspend fun saveLocalProducts(products: List<ProductDomain>)
    suspend fun getLocalProductList(): List<ProductDomain>
}