package com.junka.glo.ui.product

import app.cash.turbine.test
import com.junka.glo.testrules.CoroutinesTestRule
import com.junka.glo.ui.buildLocalProducts
import com.junka.glo.ui.buildRepositoryWith
import com.junka.glo.ui.product.ProductDetailViewModel.*
import com.junka.glo.usecases.GetProductByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import com.junka.glo.data.local.model.Product as LocalProduct
import com.junka.glo.data.remote.model.Product as RemoteProduct

@ExperimentalCoroutinesApi
class ProductDetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `UI is updated with the product`() = runTest {
        val vm = buildViewModelWith(
            id = 2,
            localData = buildLocalProducts(1, 2, 3)
        )

        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(UiState(loading = true), awaitItem())
            Assert.assertEquals(2, awaitItem().product!!.id)
            cancel()
        }
    }

    private fun buildViewModelWith(
        id: Long,
        localData: List<LocalProduct> = emptyList(),
        remoteData: List<RemoteProduct> = emptyList()
    ): ProductDetailViewModel {
        val productRepository = buildRepositoryWith(localData, remoteData)
        val getProductByIdUseCase = GetProductByIdUseCase(productRepository)
        return ProductDetailViewModel(id, getProductByIdUseCase)
    }
}