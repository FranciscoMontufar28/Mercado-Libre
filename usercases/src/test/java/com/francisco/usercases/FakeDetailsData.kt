package com.francisco.usercases

import com.francisco.domain.DetailProductDomain

object FakeDetailsData {
    fun getDetails() = DetailProductDomain(
        availableQuantity = "fake_quantity",
        imgList = emptyList(),
        warranty = "fake_warranty",
        attributes = emptyList()
    )
}