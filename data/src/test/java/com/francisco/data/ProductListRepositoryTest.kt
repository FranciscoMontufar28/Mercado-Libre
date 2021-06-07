package com.francisco.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.francisco.domain.ProductDomain
import com.francisco.domain.ProductListRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations


class ProductListRepositoryTest {

    var remoteProductListDataSource = mock<RemoteProductListDataSource>()

    var localProductListDataSource = mock<LocalProductListDataSource>()

    lateinit var productListRepository: ProductListRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productListRepository = ProductListRepositoryImpl(
            remoteProductListDataSource, localProductListDataSource
        )
        Dispatchers.Main
    }

    @Test
    fun `when get default products are fetch then result is success`() = runBlocking {
        val list = FakeDataUserCase.getProducts()

        //give
        whenever(remoteProductListDataSource.getDefaultProducts()).thenReturn(list)

        //when
        val result = productListRepository.getDefaultProducts()

        //then
        assert(result == list)
    }

    @Test
    fun `when query is type products are fetch then result is success `() = runBlocking {
        val list = FakeDataUserCase.getProducts()

        //give
        whenever(remoteProductListDataSource.getProductsByName("fake_title"))
            .thenReturn(list)

        //when
        val result = productListRepository.getProductsByName("fake_title")

        //then
        assert(result == list)
    }

    @Test
    fun `when query is type and not found then results products are not fetch`() = runBlocking {

        val list: List<ProductDomain> = listOf()

        //give
        whenever(remoteProductListDataSource.getProductsByName("fake_title"))
            .thenReturn(list)

        //when
        val result = productListRepository.getProductsByName("fake_title")

        //then
        assert(result.isEmpty())
    }

    @Test
    fun `when is no internet th products are fetch from local successful `() = runBlocking {
        val list = FakeDataUserCase.getProducts()

        //give
        whenever(localProductListDataSource.getProducts())
            .thenReturn(list)

        //when
        val result = productListRepository.getLocalProductList()

        //then
        assert(result == list)
    }
}