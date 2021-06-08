package com.francisco.data.impl

import com.francisco.data.LocalProductListDataSource
import com.francisco.data.RemoteProductListDataSource
import com.francisco.domain.ProductDomain
import com.francisco.domain.ProductListRepository
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    val remoteProductListDataSource: RemoteProductListDataSource,
    val localProductListDataSource: LocalProductListDataSource
) :
    ProductListRepository {
    override suspend fun getDefaultProducts(): List<ProductDomain> {
        return remoteProductListDataSource.getDefaultProducts()
    }

    override suspend fun getProductsByName(query: String): List<ProductDomain> {
        return remoteProductListDataSource.getProductsByName(query)
    }

    override suspend fun saveLocalProducts(products: List<ProductDomain>) {
        localProductListDataSource.saveProductList(products)
    }

    override suspend fun getLocalProductList(): List<ProductDomain> {
        return localProductListDataSource.getProducts()
    }
}