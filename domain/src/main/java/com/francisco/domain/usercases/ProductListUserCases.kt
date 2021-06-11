package com.francisco.domain.usercases

data class ProductListUserCases(
    val getDefaultProducts: GetDefaultProducts,
    val getProductsByName: GetProductsByName,
    val saveLocalProducts: SaveLocalProducts,
    val getLocalProducts: GetLocalProducts
)