package com.junka.glo.ui.home

import app.cash.turbine.test
import com.junka.glo.testrules.CoroutinesTestRule
import com.junka.glo.ui.buildLocalProducts
import com.junka.glo.ui.buildRemoteProducts
import com.junka.glo.ui.buildRepositoryWith
import com.junka.glo.ui.home.HomeViewModel.UiState
import com.junka.glo.usecases.GetProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import com.junka.glo.data.local.model.Product as LocalProduct
import com.junka.glo.data.remote.model.Product as RemoteProduct

@ExperimentalCoroutinesApi
class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from the server when local source is empty`() = runTest {
        val remoteData = buildRemoteProducts(4, 5, 6)
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData
        )

        vm.state.test {
            assertEquals(UiState(),awaitItem())
            assertEquals(UiState(loading = true),awaitItem())
            assertEquals(UiState(loading = true, products = emptyList()),awaitItem())

            val products = awaitItem().products!!
            assertEquals("Title 4", products[0].title)
            assertEquals("Title 5", products[1].title)
            assertEquals("Title 6", products[2].title)

            cancel()
        }

    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = buildLocalProducts(1, 2, 3)
        val remoteData = buildRemoteProducts(4, 5, 6)
        val vm = buildViewModelWith(
            localData = localData,
            remoteData = remoteData
        )

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(loading = true), awaitItem())

            val products = awaitItem().products!!
            assertEquals("Title 1", products[0].title)
            assertEquals("Title 2", products[1].title)
            assertEquals("Title 3", products[2].title)

            cancel()
        }
    }

    private fun buildViewModelWith(
        localData: List<LocalProduct> = emptyList(),
        remoteData: List<RemoteProduct> = emptyList()
    ): HomeViewModel {
        val productRepository = buildRepositoryWith(localData, remoteData)
        val getProductsUseCase = GetProductsUseCase(productRepository)
        return HomeViewModel(getProductsUseCase)
    }
}