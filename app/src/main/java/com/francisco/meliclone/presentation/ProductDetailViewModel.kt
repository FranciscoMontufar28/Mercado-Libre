package com.francisco.meliclone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDetailsBind
import com.francisco.meliclone.util.crashlyticsLog
import com.francisco.usercases.ProductDetailsUserCases
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(val productDetailsUserCases: ProductDetailsUserCases) :
    ViewModel() {

    sealed class ProductDetailState {
        data class ShowProducts(val products: DetailProductDomain) : ProductDetailState()
        data class UpdatedProductDetails(val productDetailsBind: ProductDetailsBind) :
            ProductDetailState()

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
                throwable.message?.let { crashlyticsLog(it) }
                _state.value =
                    ProductDetailState.UnKnownError
            }
        }
    }

    fun updateProductDetail(
        detailProductDomain: DetailProductDomain,
        productDetailsBind: ProductDetailsBind
    ) {
        productDetailsBind.imgList = detailProductDomain.imgList
        productDetailsBind.warranty = detailProductDomain.warranty
        productDetailsBind.stateSold =
            "${productDetailsBind.stateSold} | ${detailProductDomain.availableQuantity}"
        productDetailsBind.stock = detailProductDomain.availableQuantity.isNotEmpty()
        productDetailsBind.attributes = detailProductDomain.attributes
        _state.value = ProductDetailState.UpdatedProductDetails(productDetailsBind)
    }
}