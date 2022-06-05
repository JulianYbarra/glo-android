package com.junka.glo.ui.product

import app.cash.turbine.test
import com.junka.glo.testrules.CoroutinesTestRule
import com.junka.glo.testshared.sampleProduct
import com.junka.glo.ui.product.ProductDetailViewModel.*
import com.junka.glo.usecases.GetProductByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductDetailViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getProductByIdUseCase: GetProductByIdUseCase

    private lateinit var vm: ProductDetailViewModel

    val product = sampleProduct(2).first()

    @Before
    fun setup() {
        whenever(getProductByIdUseCase(2)).thenReturn(flowOf(product))
        vm = ProductDetailViewModel(2, getProductByIdUseCase)
    }

    @Test
    fun  `Ui is updated with the product`() = runTest{
        vm.state.test {
            assertEquals(UiState(),awaitItem())
            assertEquals(UiState(loading = true),awaitItem())
            assertEquals(UiState(product = product),awaitItem())
            cancel()
        }
    }
}