package com.francisco.meliclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.francisco.domain.DetailProductDomain
import com.francisco.domain.ProductDetailsBind
import com.francisco.domain.toProductDetailsBind
import com.francisco.meliclone.MercadoApp
import com.francisco.meliclone.databinding.FragmentProductDetailBinding
import com.francisco.meliclone.di.ProductDetailComponent
import com.francisco.meliclone.di.ProductDetailModule
import com.francisco.meliclone.parcelable.toProductDomain
import com.francisco.meliclone.presentation.ProductDetailViewModel
import com.francisco.meliclone.util.getViewModel

class ProductDetailFragment : Fragment() {

    private var productDetailsBind: ProductDetailsBind? = null
    private lateinit var productDetailComponent: ProductDetailComponent
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
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
        }
    }

    private fun onStateListener(state: ProductDetailViewModel.ProductDetailState) {
        when (state) {
            is ProductDetailViewModel.ProductDetailState.ShowProducts -> state.run {
                updateProductDetail(this.products)
            }
            is ProductDetailViewModel.ProductDetailState.UnKnownError -> onUnKnownError()
        }
    }

    private fun onUnKnownError() {

    }

    private fun updateProductDetail(products: DetailProductDomain) {
        productDetailsBind?.imgList = products.imgList
        productDetailsBind?.warranty = products.warranty
        productDetailsBind?.attributes = products.attributes
        productDetailsBind?.stateSold = "${productDetailsBind?.stateSold} | ${products.availableQuantity}"
        productDetailsBind?.stock = products.availableQuantity.isNotEmpty()
        binding.product = productDetailsBind
    }
}