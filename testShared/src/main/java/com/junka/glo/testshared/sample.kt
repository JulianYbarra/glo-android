package com.junka.glo.testshared

import com.junka.glo.domain.Product


fun sampleProduct(vararg ids: Long) = ids.map {
    Product(
        id = it,
        title = "Title $it",
        description = "Description $it",
        image = "Image $it"
    )
}