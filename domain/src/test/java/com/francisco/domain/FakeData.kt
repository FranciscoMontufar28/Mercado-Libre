package com.francisco.domain

import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDomain

object FakeData {
    fun getProducts(): List<ProductDomain> {
        return (0..3).map {
            ProductDomain(
                id = "fake id",
                title = "fake_title",
                img = "fake_img",
                price = "fake_price",
                freeShipping = true,
                location = "fake_location",
                condition = "fake_condition",
                mercadoPago = true
            )
        }
    }

    fun getDetail(): DetailProductDomain {
        return DetailProductDomain(
            availableQuantity = "fake_quantity",
            imgList = (0..3).map {
                "fake_img"
            },
            warranty = "fake_warranty",
            attributes = (0..3).map {
                "fake_attribute"
            }
        )
    }
}