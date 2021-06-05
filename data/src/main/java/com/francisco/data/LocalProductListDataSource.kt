package com.francisco.data

import com.francisco.domain.ProductDomain

interface LocalProductListDataSource {
    suspend fun saveProductList(productList: List<ProductDomain>)
    suspend fun getProducts(): List<ProductDomain>
}