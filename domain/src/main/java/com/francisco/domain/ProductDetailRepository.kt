package com.francisco.domain

interface ProductDetailRepository {
    suspend fun getProductDetailByItemId(id: String): DetailProductDomain
}