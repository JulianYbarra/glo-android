package com.junka.glo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.glo.common.toError
import com.junka.glo.domain.Error
import com.junka.glo.domain.Product
import com.junka.glo.domain.Resource
import com.junka.glo.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh(force: Boolean = false) {
        viewModelScope.launch {
            getProductsUseCase(force)
                .onStart { _state.update { UiState(loading = true) } }
                .catch { cause -> _state.update { UiState(error = cause.toError()) } }
                .collect { resource -> toUiState(resource) }
        }
    }

    private fun toUiState(resource : Resource<List<Product>>){
        when(resource){
            is Resource.Failure -> _state.update { UiState(error = resource.error) }
            is Resource.Loading -> _state.update { UiState(loading = resource.loading, products = resource.data) }
            is Resource.Success -> _state.update { UiState(products = resource.data) }
        }
    }

    class UiState(
        val loading: Boolean = false,
        val products: List<Product>? = null,
        val error: Error? = null
    )
}