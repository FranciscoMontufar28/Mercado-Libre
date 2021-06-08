package com.francisco.requestmanager.impl

import com.francisco.data.ProductDetailsDataSource
import com.francisco.domain.DetailProductDomain
import com.francisco.requestmanager.MercadoLibreApi
import com.francisco.requestmanager.ProductRetrofitRequest
import com.francisco.requestmanager.toDetailProductDomain
import javax.inject.Inject

class ProductDetailsDataSourceImpl @Inject constructor(val productRetrofitRequest: ProductRetrofitRequest) :
    ProductDetailsDataSource {

    /**
     * @param itemId is the id to search for details
     * @return The result of the search
     */
    override suspend fun getProductDetailByItemId(itemId: String): DetailProductDomain {
        val result = productRetrofitRequest.getService<MercadoLibreApi>().getDetailsProduct(itemId)
        return result.toDetailProductDomain()
    }
}