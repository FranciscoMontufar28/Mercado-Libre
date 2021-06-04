package com.francisco.meliclone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.usercases.ProductListUserCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(val productListUserCases: ProductListUserCases) :
    ViewModel() {

    fun getDefaultProductList() {
        viewModelScope.launch {
            val result = productListUserCases.getDefaultProducts.invoke()
        }
    }

    fun getProductListByName(query: String) {
        viewModelScope.launch {
            val result = productListUserCases.getProductsByName.invoke(query)
        }
    }
}