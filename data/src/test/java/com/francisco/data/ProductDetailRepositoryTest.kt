package com.francisco.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.francisco.domain.ProductDetailRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class ProductDetailRepositoryTest {

    var productDetailsDataSource = mock<ProductDetailsDataSource>()

    lateinit var productDetailRepository: ProductDetailRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productDetailRepository = ProductDetailRepositoryImpl(
            productDetailsDataSource
        )
        Dispatchers.Main
    }

    @Test
    fun `when item clicked the details will be getting from remote`() = runBlocking {
        val list = FakeDetailsData.getDetails()

        //give
        whenever(productDetailsDataSource.getProductDetailByItemId("fake_id")).thenReturn(list)

        //when
        val result = productDetailRepository.getProductDetailByItemId("fake_id")

        //then
        assert(result == list)
    }
}