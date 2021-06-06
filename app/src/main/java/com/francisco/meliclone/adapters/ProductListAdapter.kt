package com.francisco.meliclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francisco.domain.ProductDomain
import com.francisco.meliclone.R
import com.francisco.meliclone.databinding.ItemProductListDesignBinding

class ProductListAdapter(val onProductClicked: (ProductDomain) -> Unit) :
    ListAdapter<ProductDomain, ProductListAdapter.ProductViewHolder>(object :
        DiffUtil.ItemCallback<ProductDomain>() {
        override fun areContentsTheSame(oldItem: ProductDomain, newItem: ProductDomain): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: ProductDomain, newItem: ProductDomain): Boolean =
            oldItem == newItem
    }) {

    private var data: List<ProductDomain> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemProductListDesignBinding>(
            inflater,
            R.layout.item_product_list_design,
            parent,
            false
        )
        return ProductViewHolder(view, onProductClicked)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun setData(products: List<ProductDomain>) {
        this.data = products
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(
        val view: ItemProductListDesignBinding,
        val onProductClicked: (ProductDomain) -> Unit
    ) : RecyclerView.ViewHolder(view.root) {
        init {
            view.root.setOnClickListener {
                onProductClicked(data[adapterPosition])
            }
        }

        fun bind(item: ProductDomain) {
            view.product = item
        }
    }
}