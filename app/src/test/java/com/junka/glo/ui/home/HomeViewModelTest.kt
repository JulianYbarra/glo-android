package com.junka.glo.ui.home

import app.cash.turbine.test
import com.junka.glo.domain.Resource
import com.junka.glo.testrules.CoroutinesTestRule
import com.junka.glo.testshared.sampleProduct
import com.junka.glo.ui.home.HomeViewModel.*
import com.junka.glo.usecases.GetProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase

    private lateinit var vm : HomeViewModel

    private val products = sampleProduct(1,2,3)

    @Before
    fun setUp(){
        vm = HomeViewModel(getProductsUseCase)
    }

    @Test
    fun `state is updated with current cached content`() = runTest{

        whenever(getProductsUseCase(false)).thenReturn(flowOf(Resource.Success(products)))

        vm.state.test {
            assertEquals(UiState(),awaitItem())
            assertEquals(UiState(loading = true),awaitItem())
            assertEquals(UiState(products = products),awaitItem())
            cancel()
        }
    }
}