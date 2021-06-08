package com.francisco.requestmanager.impl

import com.francisco.data.RemoteProductListDataSource
import com.francisco.domain.ProductDomain
import com.francisco.requestmanager.MercadoLibreApi
import com.francisco.requestmanager.ProductRetrofitRequest
import com.francisco.requestmanager.toProductDomainList
import javax.inject.Inject

class RemoteProductListDataSourceImpl @Inject constructor(val productRetrofitRequest: ProductRetrofitRequest) :
    RemoteProductListDataSource {

    /**
     * @return the list of products for the api request
     */
    override suspend fun getDefaultProducts(): List<ProductDomain> {
        val result =
            productRetrofitRequest.getService<MercadoLibreApi>().getDefaultProducts().results
        return result.toProductDomainList()
    }

    /**
     * @param query is the work that the user type to looking for
     * @return the list of products for the api request
     */
    override suspend fun getProductsByName(query: String): List<ProductDomain> {
        val result =
            productRetrofitRequest.getService<MercadoLibreApi>()
                .getProductsByQuery(query = query).results
        return result.toProductDomainList()
    }
}