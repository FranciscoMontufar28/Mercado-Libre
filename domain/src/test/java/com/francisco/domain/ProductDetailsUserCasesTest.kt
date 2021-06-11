package com.francisco.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.francisco.domain.usercases.GetProductDetailsByItemId
import com.francisco.domain.usercases.ProductDetailsUserCases
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class ProductDetailsUserCasesTest {
    var productDetailRepository = mock<ProductDetailRepository>()

    lateinit var productDetailsUserCases: ProductDetailsUserCases

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productDetailsUserCases = ProductDetailsUserCases(
            GetProductDetailsByItemId(productDetailRepository)
        )
        Dispatchers.Main
    }

    @Test
    fun `when item clicked the details will be getting from remote`() = runBlocking {
        val list = FakeDetailsData.getDetails()

        //give
        whenever(productDetailRepository.getProductDetailByItemId("fake_id")).thenReturn(list)

        //when
        val result = productDetailsUserCases.getProductDetailsByItemId.invoke("fake_id")

        //then
        assert(result == list)
    }
}