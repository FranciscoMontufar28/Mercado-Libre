package com.francisco.meliclone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.francisco.meliclone.MercadoApp
import com.francisco.meliclone.databinding.FragmentProductListBinding
import com.francisco.meliclone.di.ProductListComponent
import com.francisco.meliclone.di.ProductListModule
import com.francisco.meliclone.presentation.ProductListViewModel
import com.francisco.meliclone.util.getViewModel

class ProductListFragment : Fragment() {

    private lateinit var productListComponent: ProductListComponent
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
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
        productListViewModel.getDefaultProductList()
    }
}