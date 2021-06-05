package com.francisco.meliclone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.domain.ProductDomain
import com.francisco.usercases.ProductListUserCases
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class ProductListViewModel @Inject constructor(val productListUserCases: ProductListUserCases) :
    ViewModel() {

    sealed class ProductListState {
        data class ShowProducts(val products: List<ProductDomain>) : ProductListState()
        data class ShowProductsLocally(val products: List<ProductDomain>) : ProductListState()
        object UnKnownError : ProductListState()
        object DataBaseError : ProductListState()
        object NotAvailableProduct : ProductListState()
        object HideLoader : ProductListState()
        object ShowLoader : ProductListState()
        object NoInternetConnection : ProductListState()
    }

    private val _state: MutableLiveData<ProductListState> = MutableLiveData()
    val state: LiveData<ProductListState> get() = _state

    fun getDefaultProductList() {
        _state.value = ProductListState.ShowLoader
        viewModelScope.launch {
            try {
                _state.value = ProductListState.HideLoader
                val result = productListUserCases.getDefaultProducts.invoke()
                if (result.isNotEmpty()) {
                    _state.value = ProductListState.ShowProducts(result)
                    productListUserCases.saveLocalProducts.invoke(result)
                } else {
                    _state.value = ProductListState.NotAvailableProduct
                }
            } catch (exception: Exception) {
                Timber.e(exception)
                _state.value = ProductListState.UnKnownError
            }
        }
    }

    fun getProductListByName(query: String) {
        _state.value = ProductListState.ShowLoader
        viewModelScope.launch {
            try {
                _state.value = ProductListState.HideLoader
                val result = productListUserCases.getProductsByName.invoke(query)
                if (result.isNotEmpty()) {
                    _state.value = ProductListState.ShowProducts(result)
                } else {
                    _state.value = ProductListState.NotAvailableProduct
                }
            } catch (exception: Exception) {
                Timber.e(exception)
                _state.value = ProductListState.UnKnownError
            }
        }
    }

    fun getLocalProductList() {
        _state.value = ProductListState.ShowLoader
        viewModelScope.launch {
            try {
                _state.value = ProductListState.HideLoader
                val result = productListUserCases.getLocalProducts.invoke()
                if (result.isNotEmpty()) {
                    _state.value = ProductListState.ShowProductsLocally(result)
                } else {
                    _state.value = ProductListState.NoInternetConnection
                }
            } catch (exception: Exception) {
                Timber.e(exception)
                _state.value = ProductListState.DataBaseError
            }
        }
    }
}