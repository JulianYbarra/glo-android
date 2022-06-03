package com.junka.glo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.junka.glo.domain.Product as DomainProduct

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id : Long,
    val title : String,
    val description : String,
    val image : String
)

fun Product.toDomainModel() = DomainProduct(id,title, description, image)

fun List<Product>.toDomainModel() = map { it.toDomainModel() }

fun DomainProduct.fromDomainModel() = Product(id, title, description, image)

fun List<DomainProduct>.fromDomainModel() = map{ it.fromDomainModel() }