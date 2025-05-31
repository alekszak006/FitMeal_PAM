package com.fitmeal.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fitmeal.app.database.entites.Product


@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAll(): List<Product>

    @Delete
    suspend fun delete(product: Product)
}