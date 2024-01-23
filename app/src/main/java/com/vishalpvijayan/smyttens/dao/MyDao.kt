package com.vishalpvijayan.smyttens.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vishalpvijayan.smyttens.data.ButtonEntity
import com.vishalpvijayan.smyttens.data.ProductEntity


@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertButton(button: ButtonEntity)

    @Query("SELECT * FROM product")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT COUNT(*) FROM button")
    suspend fun getCountOfRecord():Int

    @Query("SELECT * FROM button")
    suspend fun getAllButtons(): List<ButtonEntity>

}