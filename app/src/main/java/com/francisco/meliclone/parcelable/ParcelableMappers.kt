package com.francisco.meliclone.parcelable

import android.os.Parcelable
import com.francisco.domain.ProductDomain
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductListParcelable(
    val id: String,
    val title: String,
    val img: String,
    val price: String,
    val freeShipping: Boolean,
    val location: String,
    var condition: String,
    var mercadoPago: Boolean
): Parcelable

fun ProductListParcelable.toProductDomain() = ProductDomain(
    id = id,
    title = title,
    img = img,
    price = price,
    freeShipping = freeShipping,
    location = location,
    condition = condition,
    mercadoPago = mercadoPago
)

fun ProductDomain.toProductParcelable() = ProductListParcelable(
    id = id,
    title = title,
    img = img,
    price = price,
    freeShipping = freeShipping,
    location = location,
    condition = condition,
    mercadoPago = mercadoPago
)