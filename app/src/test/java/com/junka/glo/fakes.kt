package com.junka.glo

import com.junka.glo.data.local.dao.ProductDao
import com.junka.glo.data.local.model.Product as LocalProduct
import com.junka.glo.data.remote.RemoteService
import com.junka.glo.data.remote.model.Product as RemoteProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class FakeProductDao(products : List<LocalProduct> = emptyList()) : ProductDao {

    private val inMemory = MutableStateFlow(products)
    private lateinit var findProductFlow: MutableStateFlow<LocalProduct>

    override fun getList(): Flow<List<LocalProduct>>  = inMemory

    override fun getById(id: Long): Flow<LocalProduct> {
        findProductFlow = MutableStateFlow(inMemory.value.first { it.id == id })
        return findProductFlow
    }

    override suspend fun insert(products: List<LocalProduct>) {
        inMemory.value = products

        if (::findProductFlow.isInitialized) {
            products.firstOrNull() { it.id == findProductFlow.value.id }
                ?.let { findProductFlow.value = it }
        }
    }

    override suspend fun delete() {
        inMemory.value = emptyList()
    }
}

class FakeProductService(
    private val products : List<RemoteProduct> = emptyList()) : RemoteService {

    override suspend fun productList(): List<RemoteProduct> = products
}