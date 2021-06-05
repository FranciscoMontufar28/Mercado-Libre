package com.francisco.usercases.di

import com.francisco.domain.ProductDetailRepository
import com.francisco.domain.ProductListRepository
import com.francisco.usercases.*
import dagger.Module
import dagger.Provides

@Module
class UserCasesModule {

    @Provides
    fun provideProductListUserCases(productListRepository: ProductListRepository) =
        ProductListUserCases(
            GetDefaultProducts(
                productListRepository
            ),
            GetProductsByName(
                productListRepository
            ),
            SaveLocalProducts(
                productListRepository
            ),
            GetLocalProducts(
                productListRepository
            )
        )

    @Provides
    fun provideProductDetailsUserCases(productDetailRepository: ProductDetailRepository) =
        ProductDetailsUserCases(
            GetProductDetailsByItemId(
                productDetailRepository
            )
        )
}