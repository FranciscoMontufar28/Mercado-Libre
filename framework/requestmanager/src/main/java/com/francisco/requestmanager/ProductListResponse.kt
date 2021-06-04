package com.francisco.requestmanager

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("site_id")
    val siteId: String,
    val results: List<ProductsResult>
)

data class ProductsResult(
    val id: String,
    @SerializedName("site_id")
    val siteId: String,
    val title: String,
    val price: Double,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    val condition: String,
    val thumbnail: String,
    val shipping: ShippingResult,
    val address: AddressResult,
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean
)

data class ShippingResult(
    @SerializedName("free_shipping")
    val freeShipping: Boolean
)

data class AddressResult(
    @SerializedName("state_name")
    val stateName: String,
    @SerializedName("city_name")
    val cityName: String
)