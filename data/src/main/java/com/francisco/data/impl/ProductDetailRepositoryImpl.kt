package com.francisco.data.impl

import com.francisco.data.ProductDetailsDataSource
import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDetailRepository
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(val productDetailsDataSource: ProductDetailsDataSource) :
    ProductDetailRepository {
    override suspend fun getProductDetailByItemId(id: String): DetailProductDomain {
        return productDetailsDataSource.getProductDetailByItemId(id)
    }
}