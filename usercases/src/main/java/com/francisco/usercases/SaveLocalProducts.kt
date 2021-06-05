package com.francisco.usercases

import com.francisco.domain.ProductDomain
import com.francisco.domain.ProductListRepository
import javax.inject.Inject

class SaveLocalProducts @Inject constructor(val productListRepository: ProductListRepository) {
    suspend fun invoke(products: List<ProductDomain>) {
        productListRepository.saveLocalProducts(products)
    }
}