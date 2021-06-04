package com.francisco.usercases

import com.francisco.domain.ProductDomain
import com.francisco.domain.ProductListRepository
import javax.inject.Inject

class GetProductsByName @Inject constructor(val productListRepository: ProductListRepository) {
    suspend fun invoke(query: String): List<ProductDomain> {
        return productListRepository.getProductsByName(query)
    }
}