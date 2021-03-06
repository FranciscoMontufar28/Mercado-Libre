package com.francisco.meliclone.di

import android.app.Application
import com.francisco.data.di.RepositoryModule
import com.francisco.databasemanager.di.DataBaseModule
import com.francisco.domain.di.DomainModule
import com.francisco.requestmanager.di.RequestManagerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DomainModule::class,
        RepositoryModule::class,
        RequestManagerModule::class,
        DataBaseModule::class]
)
interface AppComponent {

    fun inject(module: ProductListModule): ProductListComponent
    fun inject(module: ProductDetailModule): ProductDetailComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}