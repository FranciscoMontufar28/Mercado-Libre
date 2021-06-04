package com.francisco.domain

data class DetailProductDomain(
    val availableQuantity: String,
    val imgList: List<String>,
    val warranty: String,
    val attributes: List<String>
)