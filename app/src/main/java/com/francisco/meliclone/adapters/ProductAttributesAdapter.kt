package com.francisco.meliclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francisco.meliclone.R
import com.francisco.meliclone.databinding.ProductAtributesDesignBinding

class ProductAttributesAdapter : ListAdapter<String, ProductAttributesAdapter.AttributesViewHolder>(
    object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem


    }
) {

    var data: List<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductAtributesDesignBinding>(
            inflater,
            R.layout.product_atributes_design,
            parent,
            false
        )
        return AttributesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttributesViewHolder, position: Int) {
        holder.attribute.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun setDataAttributes(attributes: List<String>) {
        this.data = attributes
        notifyDataSetChanged()
    }

    inner class AttributesViewHolder(view: ProductAtributesDesignBinding) :
        RecyclerView.ViewHolder(view.root) {
        val attribute = view.productAtributesDescription
    }
}