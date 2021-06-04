package com.francisco.data

import com.francisco.domain.ProductDomain
import com.francisco.domain.ProductListRepository
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(val remoteProductListDataSource: RemoteProductListDataSource) :
    ProductListRepository {
    override suspend fun getDefaultProducts(): List<ProductDomain> {
        return remoteProductListDataSource.getDefaultProducts()
    }

    override suspend fun getProductsByName(query: String): List<ProductDomain> {
        return remoteProductListDataSource.getProductsByName(query)
    }
}