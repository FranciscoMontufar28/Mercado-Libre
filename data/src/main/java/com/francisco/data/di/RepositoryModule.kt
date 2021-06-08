package com.francisco.data.di

import com.francisco.data.*
import com.francisco.data.impl.ProductDetailRepositoryImpl
import com.francisco.data.impl.ProductListRepositoryImpl
import com.francisco.domain.ProductDetailRepository
import com.francisco.domain.ProductListRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideProductListRepository(
        remoteProductListDataSource: RemoteProductListDataSource,
        localProductListDataSource: LocalProductListDataSource
    ): ProductListRepository =
        ProductListRepositoryImpl(remoteProductListDataSource, localProductListDataSource)

    @Provides
    fun provideProductDetailRepository(productDetailsDataSource: ProductDetailsDataSource): ProductDetailRepository =
        ProductDetailRepositoryImpl(productDetailsDataSource)
}