package com.francisco.domain

data class ProductDetailsBind(
    var stateSold: String,
    val price: String,
    val name: String,
    val freeShipping: Boolean,
    val mercadoPago: Boolean,
    val location: String = "",
    var stock: Boolean = false,
    var warranty: String = "",
    var attributes: List<String> = listOf(),
    var imgList: List<String> = listOf()
)

fun ProductDomain.toProductDetailsBind() = ProductDetailsBind(
    name = this.title,
    price = this.price,
    freeShipping = this.freeShipping,
    stateSold = this.condition,
    mercadoPago = this.mercadoPago,
    location = this.location
)