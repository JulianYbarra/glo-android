package com.junka.glo.data.local

import com.junka.glo.common.tryCall
import com.junka.glo.data.ProductLocalDataSource
import com.junka.glo.data.local.dao.ProductDao
import com.junka.glo.data.local.dao.update
import com.junka.glo.data.local.model.fromDomainModel
import com.junka.glo.data.local.model.toDomainModel
import com.junka.glo.domain.Error
import com.junka.glo.domain.Product
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDao: ProductDao
) : ProductLocalDataSource{

    override fun getList()  = productDao.getList().map { it.toDomainModel() }

    override fun getById(id : Long) = productDao.getById(id).map { it.toDomainModel() }

    override suspend fun insert(products : List<Product>) : Error? = tryCall {
        productDao.insert(products.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun delete(): Error? = tryCall {
        productDao.delete()
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun update(products: List<Product>) : Error? = tryCall{
        productDao.update(products.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
}