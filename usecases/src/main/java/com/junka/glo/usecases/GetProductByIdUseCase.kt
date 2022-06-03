package com.junka.glo.usecases

import com.junka.glo.data.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id : Long) = productRepository.getProductById(id)
}