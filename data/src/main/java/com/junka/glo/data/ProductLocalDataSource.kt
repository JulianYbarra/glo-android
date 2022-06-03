package com.junka.glo.data

import com.junka.glo.domain.Error
import com.junka.glo.domain.Product
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    fun getList() : Flow<List<Product>>
    fun getById(id : Long) : Flow<Product>

    suspend fun insert(products : List<Product>) : Error?
    suspend fun delete() : Error?
    suspend fun update(products: List<Product>) : Error?
}