package com.junka.glo.data

import arrow.core.Either
import com.junka.glo.domain.Error
import com.junka.glo.domain.Product

interface ProductRemoteDataSource {
    suspend fun getProductList() : Either<Error, List<Product>>
}