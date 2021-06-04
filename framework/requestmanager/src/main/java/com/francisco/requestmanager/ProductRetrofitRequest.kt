package com.francisco.requestmanager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRetrofitRequest(baseUrl: String) : BaseRequest<MercadoLibreApi>(baseUrl)

abstract class BaseRequest<T : Any>(var baseUrl: String) {
    inline fun <reified T : Any> getService(): T =
        buildRetrofit().run {
            create(T::class.java)
        }

    fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}