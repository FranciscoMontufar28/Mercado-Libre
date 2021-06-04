package com.francisco.data.di

import com.francisco.data.ProductDetailRepositoryImpl
import com.francisco.data.ProductDetailsDataSource
import com.francisco.data.ProductListRepositoryImpl
import com.francisco.data.RemoteProductListDataSource
import com.francisco.domain.ProductDetailRepository
import com.francisco.domain.ProductListRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideProductListRepository(remoteProductListDataSource: RemoteProductListDataSource): ProductListRepository =
        ProductListRepositoryImpl(remoteProductListDataSource)

    @Provides
    fun provideProductDetailRepository(productDetailsDataSource: ProductDetailsDataSource): ProductDetailRepository =
        ProductDetailRepositoryImpl(productDetailsDataSource)
}