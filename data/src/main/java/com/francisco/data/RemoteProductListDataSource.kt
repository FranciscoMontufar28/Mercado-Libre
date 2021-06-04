package com.francisco.data

import com.francisco.domain.ProductDomain

interface RemoteProductListDataSource {
    suspend fun getDefaultProducts(): List<ProductDomain>
    suspend fun getProductsByName(query: String): List<ProductDomain>
}