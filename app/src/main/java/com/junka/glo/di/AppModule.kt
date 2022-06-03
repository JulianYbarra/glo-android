package com.junka.glo.di

import android.app.Application
import androidx.room.Room
import com.junka.glo.data.local.LocalDataBase
import com.junka.glo.data.remote.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "http://private-f0eea-mobilegllatam.apiary-mock.com/"


    @Provides
    @Singleton
    fun provideDataBase(application: Application) = Room.databaseBuilder(
        application,
        LocalDataBase::class.java,
        "glo-db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideProductDao(dataBase: LocalDataBase) = dataBase.productDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): RemoteService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}