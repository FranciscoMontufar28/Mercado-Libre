package com.francisco.usercases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.francisco.domain.ProductListRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class ProductListUserCasesTest {

    var productListRepository = mock<ProductListRepository>()

    lateinit var productListUserCases: ProductListUserCases

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productListUserCases = ProductListUserCases(
            GetDefaultProducts(productListRepository),
            GetProductsByName(productListRepository),
            SaveLocalProducts(productListRepository),
            GetLocalProducts(productListRepository)
        )
        Dispatchers.Main
    }

    @Test
    fun `when get default products are fetch then result is success`() = runBlocking {
        val list = FakeData.getProducts()

        //give
        whenever(productListRepository.getDefaultProducts()).thenReturn(list)

        //when
        val result = productListUserCases.getDefaultProducts.invoke()

        //then
        assert(result == list)
    }

    @Test
    fun `when get products using query are fetch then result is success`() = runBlocking {
        val list = FakeData.getProducts()

        //give
        whenever(productListRepository.getProductsByName("fake_query")).thenReturn(list)

        //when
        val result = productListUserCases.getProductsByName.invoke("fake_query")

        //then
        assert(result == list)
    }

    @Test
    fun `when app is offline products are fetch from local then result is success`() = runBlocking {
        val list = FakeData.getProducts()

        //give
        whenever(productListRepository.getLocalProductList()).thenReturn(list)

        //when
        val result = productListUserCases.getLocalProducts.invoke()

        //then
        assert(result == list)
    }
}