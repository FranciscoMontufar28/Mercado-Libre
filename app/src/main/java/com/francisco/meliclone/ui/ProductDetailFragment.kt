package com.francisco.meliclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDetailsBind
import com.francisco.domain.toProductDetailsBind
import com.francisco.meliclone.MercadoApp
import com.francisco.meliclone.adapters.ProductAttributesAdapter
import com.francisco.meliclone.adapters.ProductListAdapter
import com.francisco.meliclone.databinding.FragmentProductDetailBinding
import com.francisco.meliclone.di.ProductDetailComponent
import com.francisco.meliclone.di.ProductDetailModule
import com.francisco.meliclone.parcelable.toProductDomain
import com.francisco.meliclone.presentation.ProductDetailViewModel
import com.francisco.meliclone.util.getViewModel
import timber.log.Timber

class ProductDetailFragment : Fragment() {

    private var productDetailsBind: ProductDetailsBind? = null
    private lateinit var productDetailComponent: ProductDetailComponent
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val productAttributesAdapter by lazy { ProductAttributesAdapter() }
    private val productDetailViewModel: ProductDetailViewModel by lazy {
        getViewModel { productDetailComponent.productDetailViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailComponent =
            (requireActivity().application as MercadoApp).getAppComponent().inject(
                ProductDetailModule()
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let {
            ProductDetailFragmentArgs.fromBundle(it).product.toProductDomain()
        }
        if (args != null) {
            productDetailsBind = args.toProductDetailsBind()
            productDetailViewModel.getProductDetailsByItemId(args.id)
            productDetailViewModel.state.observe(viewLifecycleOwner, Observer(::onStateListener))
            setUpAttributesRecycler()
        } else {
            Timber.e("no arguments available")
        }
    }

    private fun setUpAttributesRecycler() {
        binding.productDetailsAtributes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAttributesAdapter
        }
    }

    private fun onStateListener(state: ProductDetailViewModel.ProductDetailState) {
        when (state) {
            is ProductDetailViewModel.ProductDetailState.ShowProducts -> state.run {
                updateProductDetail(this.products)
            }
            is ProductDetailViewModel.ProductDetailState.UnKnownError -> onUnKnownError()
            is ProductDetailViewModel.ProductDetailState.UpdatedProductDetails -> state.run {
                onProductDetailUpdated(
                    this.productDetailsBind
                )
            }
        }
    }

    private fun onUnKnownError() {
        showToast("There is am unknown error, try again")
    }

    private fun updateProductDetail(products: DetailProductDomain) {
        productDetailsBind?.let { productDetailViewModel.updateProductDetail(products, it) }
    }

    private fun onProductDetailUpdated(productDetailsBind: ProductDetailsBind) {
        productAttributesAdapter.setDataAttributes(productDetailsBind.attributes)
        binding.product = productDetailsBind
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}