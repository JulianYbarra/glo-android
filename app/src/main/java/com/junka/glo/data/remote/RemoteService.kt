package com.junka.glo.data.remote

import com.junka.glo.data.remote.model.Product
import retrofit2.http.GET

interface RemoteService {

    @GET("list")
    suspend fun productList() : List<Product>
}