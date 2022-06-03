package com.junka.glo.ui.product.di

import androidx.lifecycle.SavedStateHandle
import com.junka.glo.di.ProductId
import com.junka.glo.ui.product.ProductDetailFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @ProductId
    fun provideMovieId(savedStateHandle: SavedStateHandle) =
        ProductDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id

}