package com.francisco.meliclone.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.francisco.domain.ProductDomain
import com.francisco.meliclone.MercadoApp
import com.francisco.meliclone.R
import com.francisco.meliclone.adapters.ProductListAdapter
import com.francisco.meliclone.databinding.FragmentProductListBinding
import com.francisco.meliclone.di.ProductListComponent
import com.francisco.meliclone.di.ProductListModule
import com.francisco.meliclone.parcelable.toProductParcelable
import com.francisco.meliclone.presentation.ProductListViewModel
import com.francisco.meliclone.util.GridItemDecoration
import com.francisco.meliclone.util.LifeCycleDisposable
import com.francisco.meliclone.util.NetworkUtil
import com.francisco.meliclone.util.getViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ProductListFragment : Fragment() {

    var subject: PublishSubject<String> = PublishSubject.create()
    private val disposable: LifeCycleDisposable = LifeCycleDisposable(this)
    private var searchQuery: String? = null
    private lateinit var productListComponent: ProductListComponent
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val productListAdapter by lazy { ProductListAdapter(::navigateToProductDetails) }
    private val productListViewModel: ProductListViewModel by lazy {
        getViewModel { productListComponent.productListViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListComponent =
            (requireActivity().application as MercadoApp).getAppComponent().inject(
                ProductListModule()
            )
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        onSearchProduct(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onSearchProduct(searchView: SearchView) {
        disposable.add(onSearch(searchView)
            .debounce(1000, TimeUnit.MILLISECONDS)
            ?.filter { text -> text.isNotEmpty() }
            ?.map { text -> text.toLowerCase().trim() }
            ?.distinctUntilChanged()
            ?.switchMap { query -> PublishSubject.just(query) }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ query ->
                searchQuery = query
                productListViewModel.getProductListByName(query)
            }, {
                Timber.e(it)
            })
        )
    }

    private fun onSearch(searchView: SearchView): PublishSubject<String> {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subject.onNext(newText)
                return false
            }
        })
        return subject
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
        binding.noInternetView.root.visibility = View.VISIBLE
        binding.noItemsFoundView.root.visibility = View.GONE
        binding.productListRecyclerView.visibility = View.GONE
    }

    private fun onDataBaseError() {
        showToast("There is an error getting the results from the database")
    }

    private fun updateProductsInList(products: List<ProductDomain>) {
        binding.productListRecyclerView.visibility = View.VISIBLE
        binding.noInternetView.root.visibility = View.GONE
        binding.noItemsFoundView.root.visibility = View.GONE
        productListAdapter.setData(products)
    }

    private fun onNotAvailableProduct() {
        binding.noItemsFoundView.root.visibility = View.VISIBLE
        binding.productListRecyclerView.visibility = View.GONE
        binding.noInternetView.root.visibility = View.GONE
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
        if (searchQuery.isNullOrEmpty()) {
            productListViewModel.getDefaultProductList()
        } else {
            productListViewModel.getProductListByName(searchQuery!!)
        }
    }

    private fun navigateToProductDetails(product: ProductDomain) {
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(product.toProductParcelable())
        findNavController().navigate(action)
    }
}