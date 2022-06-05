package com.junka.glo.usecases

import com.junka.glo.domain.Resource
import com.junka.glo.testshared.sampleProduct
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetProductsUseCaseTest{

    @Test
    fun `Invoke calls product repository`() : Unit = runBlocking {
        val products = flowOf(Resource.Success(sampleProduct(1,2,3)))

        val getProducts = GetProductsUseCase(mock(){
            on { getProductList(false) } doReturn(products)
        })

        val result = getProducts(false)

        assertEquals(products,result)
    }
}

