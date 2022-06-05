package com.junka.glo.usecases

import com.junka.glo.testshared.sampleProduct
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetProductByIdUseCaseTest{

    @Test
    fun `Invoke call product repository`() : Unit = runBlocking {
        val product = flowOf(sampleProduct(1).first())
        val findProduct = GetProductByIdUseCase(mock(){
            on { getProductById(1) } doReturn (product)
        })

        val result = findProduct(1)
        assertEquals(product,result)
    }
}