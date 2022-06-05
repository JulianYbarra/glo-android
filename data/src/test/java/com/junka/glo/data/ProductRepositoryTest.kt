package com.junka.glo.data

import app.cash.turbine.test
import arrow.core.right
import com.junka.glo.domain.Resource
import com.junka.glo.testshared.sampleProduct
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryTest {

    @Mock
    lateinit var localDataSource: ProductLocalDataSource

    @Mock
    lateinit var remoteDataSource: ProductRemoteDataSource


    private lateinit var productRepository: ProductRepository

    private val localProducts = sampleProduct(1, 2, 3)

    @Before
    fun setUp() {
        productRepository = ProductRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `product are taken from local data source`(): Unit = runBlocking {

        whenever(localDataSource.getList()).thenReturn(flowOf(localProducts))

        val result = productRepository.getProductList(false).first()

        assertEquals(Resource.Success(localProducts), result)
    }

    @Test
    fun `find a product by id is done in local data source`(): Unit = runBlocking {
        val product = flowOf(sampleProduct(1).first())
        whenever(localDataSource.getById(1)).thenReturn(product)

        val result = productRepository.getProductById(1)

        assertEquals(product, result)
    }

    @Test
    fun `products are saved to local data source`() : Unit = runBlocking {
        val remoteProduct = sampleProduct(4,5,6)

        whenever(localDataSource.getList()).thenReturn(flowOf(localProducts))

        whenever(remoteDataSource.getProductList()).thenReturn(remoteProduct.right())

        productRepository.getProductList(true).test {

            assertEquals(Resource.Loading(true,localProducts), awaitItem())
            assertEquals(Resource.Success(localProducts), awaitItem())
            awaitComplete()
        }

        verify(localDataSource).update(remoteProduct)
    }
}