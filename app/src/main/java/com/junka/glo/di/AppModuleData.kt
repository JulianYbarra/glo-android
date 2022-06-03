package com.junka.glo.di

import com.junka.glo.data.ProductLocalDataSource
import com.junka.glo.data.ProductRemoteDataSource
import com.junka.glo.data.local.LocalDataSource
import com.junka.glo.data.remote.ServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleData {
    @Binds
    abstract fun bindProductRemoteDataSource(serverDataSource: ServerDataSource) : ProductRemoteDataSource

    @Binds
    abstract fun bindProductLocalDataSource(localDataSource: LocalDataSource) : ProductLocalDataSource

}