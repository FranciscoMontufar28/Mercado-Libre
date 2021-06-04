package com.francisco.meliclone

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.francisco.meliclone.di.AppComponent
import com.francisco.meliclone.di.DaggerAppComponent

class MercadoApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        appComponent = initAppComponent()
    }

    private fun initAppComponent() = DaggerAppComponent.factory().create(this)

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}