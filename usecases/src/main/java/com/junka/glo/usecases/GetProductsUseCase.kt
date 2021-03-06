package com.junka.glo.usecases

import com.junka.glo.data.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(force : Boolean) = productRepository.getProductList(force)
}