package com.francisco.requestmanager.di

import com.francisco.data.ProductDetailsDataSource
import com.francisco.data.RemoteProductListDataSource
import com.francisco.requestmanager.APIConstants.BASE_URL
import com.francisco.requestmanager.impl.ProductDetailsDataSourceImpl
import com.francisco.requestmanager.ProductRetrofitRequest
import com.francisco.requestmanager.impl.RemoteProductListDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RequestManagerModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideProductRetrofitRequest(
        @Named("baseUrl") baseUrl: String
    ) = ProductRetrofitRequest(baseUrl)

    @Provides
    fun provideRemoteProductListDataSource(productRetrofitRequest: ProductRetrofitRequest): RemoteProductListDataSource =
        RemoteProductListDataSourceImpl(productRetrofitRequest)

    @Provides
    fun provideProductDetailsDataSource(productRetrofitRequest: ProductRetrofitRequest): ProductDetailsDataSource =
        ProductDetailsDataSourceImpl(productRetrofitRequest)
}