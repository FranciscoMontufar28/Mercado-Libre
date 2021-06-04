package com.francisco.usercases

import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDetailRepository
import javax.inject.Inject

class GetProductDetailsByItemId @Inject constructor(val productDetailRepository: ProductDetailRepository) {
    suspend fun invoke(itemId: String): DetailProductDomain {
        return productDetailRepository.getProductDetailByItemId(itemId)
    }
}