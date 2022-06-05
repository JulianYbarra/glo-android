package com.junka.glo.data

import com.junka.glo.data.common.networkBoundResource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
) {

    fun getProductList(force : Boolean) = networkBoundResource(
        query = { productLocalDataSource.getList() },
        fetch = { productRemoteDataSource.getProductList() },
        saveFetchResult = { products -> productLocalDataSource.update(products) },
        shouldFetch = { local -> local.isEmpty() || force }
    )

    fun getProductById(id : Long) = productLocalDataSource.getById(id)
}