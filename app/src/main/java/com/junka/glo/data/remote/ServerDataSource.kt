package com.junka.glo.data.remote

import com.junka.glo.common.tryCall
import com.junka.glo.data.remote.model.toDomainModel
import com.junka.glo.data.ProductRemoteDataSource
import javax.inject.Inject

class ServerDataSource @Inject constructor(
    private val remoteService: RemoteService
) : ProductRemoteDataSource {

    override suspend fun getProductList() = tryCall {
        remoteService.productList().toDomainModel()
    }
}