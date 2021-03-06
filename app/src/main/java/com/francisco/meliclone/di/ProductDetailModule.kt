package com.francisco.meliclone.di

import com.francisco.domain.usercases.ProductDetailsUserCases
import com.francisco.meliclone.presentation.ProductDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ProductDetailModule {

    @Provides
    fun provideProductDetailViewModel(productDetailsUserCases: ProductDetailsUserCases) =
        ProductDetailViewModel(productDetailsUserCases)
}

@Subcomponent(modules = [ProductDetailModule::class])
interface ProductDetailComponent {
    val productDetailViewModel: ProductDetailViewModel
}