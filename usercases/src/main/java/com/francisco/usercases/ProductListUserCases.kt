package com.francisco.usercases

data class ProductListUserCases(
    val getDefaultProducts: GetDefaultProducts,
    val getProductsByName: GetProductsByName,
    val saveLocalProducts: SaveLocalProducts,
    val getLocalProducts: GetLocalProducts
)