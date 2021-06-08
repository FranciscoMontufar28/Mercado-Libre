package com.francisco.databasemanager.impl

import com.francisco.data.LocalProductListDataSource
import com.francisco.databasemanager.ProductDataBase
import com.francisco.databasemanager.toListOfProductDomain
import com.francisco.databasemanager.toListOfProductsEntity
import com.francisco.domain.ProductDomain
import javax.inject.Inject

class LocalProductListDataSourceImpl @Inject constructor(val productDataBase: ProductDataBase) :
    LocalProductListDataSource {

    /**
     * @param productList is the list of items to be stored in the local database
     */
    override suspend fun saveProductList(productList: List<ProductDomain>) {
        val dataBase = productDataBase.productDao()
        dataBase.deleteAllProducts()
        dataBase.insertAllProducts(productList.toListOfProductsEntity())
    }

    /**
     * @return provide the products in the local database
     */
    override suspend fun getProducts(): List<ProductDomain> {
        val dataBase = productDataBase.productDao()
        return dataBase.getAllProducts().toListOfProductDomain()
    }
}