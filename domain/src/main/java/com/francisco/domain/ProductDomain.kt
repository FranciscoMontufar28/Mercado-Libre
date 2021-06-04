package com.francisco.domain

data class ProductDomain(
    val id: String,
    val title: String?,
    val img: String?,
    val price: String?,
    val freeShipping: Boolean,
    val location: String?,
    var condition: String?,
    var mercadoPago: Boolean
)