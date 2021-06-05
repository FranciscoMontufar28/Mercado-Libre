package com.francisco.databasemanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductListEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val id: String,
    @ColumnInfo(name = "product_title")
    val title: String?,
    @ColumnInfo(name = "product_img")
    val img: String?,
    @ColumnInfo(name = "product_price")
    val price: String?,
    @ColumnInfo(name = "product_free_shipping")
    val freeShipping: Boolean,
    @ColumnInfo(name = "product_location")
    val location: String?,
    @ColumnInfo(name = "product_condition")
    var condition: String?,
    @ColumnInfo(name = "product_mercado_pago")
    var mercadoPago: Boolean
)