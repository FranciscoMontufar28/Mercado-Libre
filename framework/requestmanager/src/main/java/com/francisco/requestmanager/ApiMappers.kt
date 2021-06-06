package com.francisco.requestmanager

import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDomain

fun ProductsResult.toProductDomain() = ProductDomain(
    id = this.id,
    title = this.title,
    price = "$${this.price}",
    img = this.thumbnail,
    freeShipping = this.shipping.freeShipping,
    location = "${this.address.cityName} | ${this.address.stateName}",
    condition = if (this.condition == "new") "Nuevo" else "Usado",
    mercadoPago = this.acceptsMercadopago
)

fun ProductDetailResponse.toDetailProductDomain(): DetailProductDomain {
    val imageList: ArrayList<String> = arrayListOf()
    val attributesList: ArrayList<String> = arrayListOf()
    this.pictures.forEach {
        imageList.add(it.url)
    }
    this.attributes.forEach {
        attributesList.add("${it.name}: ${it.valueName}")
    }
    return DetailProductDomain(
        warranty = this.warranty,
        imgList = imageList,
        availableQuantity = "${this.availableQuantity} disponibles",
        attributes = attributesList
    )
}

fun List<ProductsResult>.toProductDomainList() = map(ProductsResult::toProductDomain)