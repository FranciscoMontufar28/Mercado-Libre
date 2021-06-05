package com.francisco.databasemanager

import com.francisco.domain.ProductDomain

fun ProductListEntity.toProductListDomain() = ProductDomain(
    id = id,
    title = title,
    img = img,
    price = price,
    freeShipping = freeShipping,
    location = location,
    condition = condition,
    mercadoPago = mercadoPago
)

fun ProductDomain.toProductListEntity() = ProductListEntity(
    id = id,
    title = title,
    img = img,
    price = price,
    freeShipping = freeShipping,
    location = location,
    condition = condition,
    mercadoPago = mercadoPago
)

fun List<ProductDomain>.toListOfProductsEntity() = map(ProductDomain::toProductListEntity)
fun List<ProductListEntity>.toListOfProductDomain() = map(ProductListEntity::toProductListDomain)