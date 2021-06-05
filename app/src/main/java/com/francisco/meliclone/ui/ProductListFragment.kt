package com.francisco.meliclone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.francisco.domain.ProductDomain
import com.francisco.meliclone.MercadoApp
import com.francisco.meliclone.adapters.ProductListAdapter
import com.francisco.meliclone.databinding.FragmentProductListBinding
import com.francisco.meliclone.di.ProductListComponent
import com.francisco.meliclone.di.ProductListModule
import com.francisco.meliclone.presentation.ProductListViewModel
import com.francisco.meliclone.util.GridItemDecoration
import com.francisco.meliclone.util.NetworkUtil
import com.francisco.meliclone.util.getViewModel

class ProductListFragment : Fragment() {

    private lateinit var productListComponent: ProductListComponent
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val productListAdapter by lazy { ProductListAdapter() }
    private val productListViewModel: ProductListViewModel by lazy {
        getViewModel { productListComponent.productListViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListComponent =
            (requireActivity().application as MercadoApp).getAppComponent().inject(
                ProductListModule()
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProductListRecyclerView()
        observeProductListState()
        networkStatusListener()
    }

    private fun observeProductListState() {
        productListViewModel.state.observe(viewLifecycleOwner, Observer(::onStateListener))
    }

    private fun onStateListener(state: ProductListViewModel.ProductListState) {
        when (state) {
            is ProductListViewModel.ProductListState.ShowProducts -> state.run {
                updateProductsInList(this.products)
            }
            is ProductListViewModel.ProductListState.NotAvailableProduct -> onNotAvailableProduct()
            is ProductListViewModel.ProductListState.UnKnownError -> onUnKnownError()
            is ProductListViewModel.ProductListState.ShowLoader -> showLoader()
            is ProductListViewModel.ProductListState.HideLoader -> hideLoader()
            is ProductListViewModel.ProductListState.DataBaseError -> onDataBaseError()
            is ProductListViewModel.ProductListState.NoInternetConnection -> onNoInternetConnection()
            is ProductListViewModel.ProductListState.ShowProductsLocally -> state.run {
                onShowProductsLocally(this.products)
            }
        }
    }

    private fun onShowProductsLocally(products: List<ProductDomain>) {
        updateProductsInList(products)
        showToast("No internet connection")
    }

    private fun onNoInternetConnection() {
        showToast("Nada que mostrar")
    }

    private fun onDataBaseError() {
        showToast("There is an error getting the results from the database")
    }

    private fun updateProductsInList(products: List<ProductDomain>) {
        productListAdapter.setData(products)
    }

    private fun onNotAvailableProduct() {

    }

    private fun showLoader() {
        binding.productListProgressBar.visibility = View.VISIBLE
    }

    private fun onUnKnownError() {
        showToast("There is an error getting the results")
    }

    private fun hideLoader() {
        binding.productListProgressBar.visibility = View.GONE
    }

    private fun setUpProductListRecyclerView() {
        binding.productListRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(
                GridItemDecoration(
                    gridSpacingPx = 30,
                    gridSize = 2
                )
            )
            adapter = productListAdapter
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun networkStatusListener() {
        NetworkUtil.getNetworkLiveData(requireContext())
            .observe(viewLifecycleOwner, Observer { isConnected ->
                if (isConnected) {
                    onApplicationConnected()
                } else {
                    productListViewModel.getLocalProductList()
                }
            })
    }

    private fun onApplicationConnected() {
        
    }
}