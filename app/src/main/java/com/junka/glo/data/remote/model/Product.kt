package com.junka.glo.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import com.junka.glo.domain.Product as DomainProduct

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "description")
    val description: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "title")
    val title: String
)


fun Product.toDomainModel() = DomainProduct(0,title, description, image)

fun List<Product>.toDomainModel() = map{it.toDomainModel()}