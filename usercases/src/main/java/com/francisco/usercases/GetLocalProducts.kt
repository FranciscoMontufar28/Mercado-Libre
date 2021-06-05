package com.francisco.usercases

import com.francisco.domain.ProductDomain
import com.francisco.domain.ProductListRepository
import javax.inject.Inject

class GetLocalProducts @Inject constructor(val productListRepository: ProductListRepository) {
    suspend fun invoke(): List<ProductDomain> {
        return productListRepository.getLocalProductList()
    }
}