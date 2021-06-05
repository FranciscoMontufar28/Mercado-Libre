package com.francisco.databasemanager

import com.francisco.data.LocalProductListDataSource
import com.francisco.domain.ProductDomain
import javax.inject.Inject

class LocalProductListDataSourceImpl @Inject constructor(val productDataBase: ProductDataBase) :
    LocalProductListDataSource {
    override suspend fun saveProductList(productList: List<ProductDomain>) {
        val dataBase = productDataBase.productDao()
        dataBase.deleteAllProducts()
        dataBase.insertAllProducts(productList.toListOfProductsEntity())
    }

    override suspend fun getProducts(): List<ProductDomain> {
        val dataBase = productDataBase.productDao()
        return dataBase.getAllProducts().toListOfProductDomain()
    }
}