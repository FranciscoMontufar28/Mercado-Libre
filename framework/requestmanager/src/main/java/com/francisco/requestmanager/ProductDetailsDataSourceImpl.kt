package com.francisco.requestmanager

import com.francisco.data.ProductDetailsDataSource
import com.francisco.domain.DetailProductDomain
import javax.inject.Inject

class ProductDetailsDataSourceImpl @Inject constructor(val productRetrofitRequest: ProductRetrofitRequest) :
    ProductDetailsDataSource {
    override suspend fun getProductDetailByItemId(itemId: String): DetailProductDomain {
        val result = productRetrofitRequest.getService<MercadoLibreApi>().getDetailsProduct(itemId)
        return result.toDetailProductDomain()
    }
}