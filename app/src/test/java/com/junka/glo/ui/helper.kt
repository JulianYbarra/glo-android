package com.junka.glo.ui

import com.junka.glo.FakeProductDao
import com.junka.glo.FakeProductService
import com.junka.glo.data.ProductRepository
import com.junka.glo.data.local.LocalDataSource
import com.junka.glo.data.remote.ServerDataSource
import com.junka.glo.data.local.model.Product as LocalProduct
import com.junka.glo.data.remote.model.Product as RemoteProduct

fun buildRepositoryWith(
    localData: List<LocalProduct>,
    remoteData: List<RemoteProduct>
): ProductRepository {

    val localDataSource = LocalDataSource(FakeProductDao(localData))
    val remoteDataSource = ServerDataSource(FakeProductService(remoteData))
    return ProductRepository(localDataSource, remoteDataSource)
}

fun buildLocalProducts(vararg id: Long) = id.map {
    LocalProduct(
        id = it,
        title = "Title $it",
        description = "Description $it",
        image = "Image $it"
    )
}

fun buildRemoteProducts(vararg id: Long) = id.map {
    RemoteProduct(
        title = "Title $it",
        description = "Description $it",
        image = "Image $it"
    )
}