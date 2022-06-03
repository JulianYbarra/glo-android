package com.junka.glo.data.local.dao

import androidx.room.*
import com.junka.glo.data.local.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getList() : Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getById(id : Long) : Flow<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: List<Product>)

    @Query("DELETE FROM product")
    suspend fun delete()
}

@Transaction
suspend fun ProductDao.update(product: List<Product>){
    delete()
    insert(product)
}