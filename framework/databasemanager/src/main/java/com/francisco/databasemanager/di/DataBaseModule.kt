package com.francisco.databasemanager.di

import android.app.Application
import com.francisco.data.LocalProductListDataSource
import com.francisco.databasemanager.impl.LocalProductListDataSourceImpl
import com.francisco.databasemanager.ProductDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(app: Application): ProductDataBase = ProductDataBase.invoke(app)

    @Provides
    fun provideLocalProductListDataSource(dataBase: ProductDataBase): LocalProductListDataSource =
        LocalProductListDataSourceImpl(dataBase)
}