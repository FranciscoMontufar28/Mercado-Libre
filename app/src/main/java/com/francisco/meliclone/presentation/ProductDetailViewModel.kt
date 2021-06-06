package com.francisco.meliclone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.domain.DetailProductDomain
import com.francisco.usercases.ProductDetailsUserCases
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(val productDetailsUserCases: ProductDetailsUserCases) :
    ViewModel() {

    sealed class ProductDetailState {
        data class ShowProducts(val products: DetailProductDomain) : ProductDetailState()
        object UnKnownError : ProductDetailState()
    }

    private val _state: MutableLiveData<ProductDetailState> =
        MutableLiveData()

    val state: LiveData<ProductDetailState> get() = _state

    fun getProductDetailsByItemId(itemId: String) {
        viewModelScope.launch {
            try {
                val result = productDetailsUserCases.getProductDetailsByItemId.invoke(itemId)
                _state.value = ProductDetailState.ShowProducts(result)
            } catch (throwable: Throwable) {
                Timber.e(throwable)
                _state.value =
                    ProductDetailState.UnKnownError
            }
        }
    }
}