package com.francisco.requestmanager

import com.francisco.data.RemoteProductListDataSource
import com.francisco.domain.ProductDomain
import javax.inject.Inject

class RemoteProductListDataSourceImpl @Inject constructor(val productRetrofitRequest: ProductRetrofitRequest) :
    RemoteProductListDataSource {
    override suspend fun getDefaultProducts(): List<ProductDomain> {
        val result =
            productRetrofitRequest.getService<MercadoLibreApi>().getDefaultProducts().results
        return result.toProductDomainList()
    }

    override suspend fun getProductsByName(query: String): List<ProductDomain> {
        val result =
            productRetrofitRequest.getService<MercadoLibreApi>()
                .getProductsByQuery(query = query).results
        return result.toProductDomainList()
    }
}