package com.junka.glo.data

import com.junka.glo.domain.Product
import com.junka.glo.domain.Resource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDataSource: ProductRemoteDataSource
) {

    suspend fun getProductList() : Resource<List<Product>> {
       productDataSource.getProductList().fold<Nothing>(
           ifLeft = { return Resource.Failure(it) },
           ifRight = { return Resource.Success(it) }
       )
    }
}