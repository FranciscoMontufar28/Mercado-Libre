package com.francisco.requestmanager

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    val pictures: List<Images>,
    val warranty: String,
    val attributes: List<Attributes>
)

data class Images(
    val url: String
)

data class Attributes(
    val name: String,
    @SerializedName("value_name")
    val valueName: String
)