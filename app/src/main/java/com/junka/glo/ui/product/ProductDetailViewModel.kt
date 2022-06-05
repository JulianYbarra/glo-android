package com.junka.glo.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.glo.common.toError
import com.junka.glo.di.ProductId
import com.junka.glo.domain.Error
import com.junka.glo.domain.Product
import com.junka.glo.usecases.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    @ProductId id : Long,
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getProductByIdUseCase(id)
                .onStart { _state.update { UiState(loading = true) } }
                .catch { cause -> _state.update { UiState(error = cause.toError() ) } }
                .collect { product -> _state.update { UiState(product = product) }}
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val product : Product? = null,
        val error : Error? = null
    )
}