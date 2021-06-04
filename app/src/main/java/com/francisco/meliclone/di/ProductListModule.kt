package com.francisco.meliclone.di

import com.francisco.meliclone.presentation.ProductListViewModel
import com.francisco.usercases.ProductListUserCases
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ProductListModule {
    @Provides
    fun provideProductListViewModel(productListUserCases: ProductListUserCases) =
        ProductListViewModel(productListUserCases)
}

@Subcomponent(modules = [ProductListModule::class])
interface ProductListComponent {
    val productListViewModel: ProductListViewModel
}