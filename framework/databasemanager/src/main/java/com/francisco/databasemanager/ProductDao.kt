package com.francisco.databasemanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productList: List<ProductListEntity>)

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<ProductListEntity>

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()
}