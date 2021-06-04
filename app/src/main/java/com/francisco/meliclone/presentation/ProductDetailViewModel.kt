package com.francisco.meliclone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.usercases.ProductDetailsUserCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(val productDetailsUserCases: ProductDetailsUserCases) :
    ViewModel() {

    fun getProductDetailsByItemId(itemId: String) {
        viewModelScope.launch {
            productDetailsUserCases.getProductDetailsByItemId.invoke(itemId)
        }
    }
}