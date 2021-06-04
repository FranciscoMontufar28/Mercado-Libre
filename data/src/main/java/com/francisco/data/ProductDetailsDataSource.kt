package com.francisco.data

import com.francisco.domain.DetailProductDomain

interface ProductDetailsDataSource {
    suspend fun getProductDetailByItemId(itemId: String): DetailProductDomain
}