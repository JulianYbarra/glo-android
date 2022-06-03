package com.junka.glo.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.junka.glo.R
import com.junka.glo.databinding.ViewProductBinding
import com.junka.glo.domain.Product
import com.junka.glo.ui.common.basicDiffUtil
import com.junka.glo.ui.common.inflate

class ProductAdapter(
    private val onItemClick : (Product) -> Unit)
    : ListAdapter<Product, ProductAdapter.ViewHolder>(basicDiffUtil{ old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_product, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = ViewProductBinding.bind(view)

        fun bind(product: Product) = with(binding){
            titleTv.text = product.title
            descriptionTv.text = product.description
            imageIv.load(product.image){
                crossfade(true)
                error(R.drawable.ic_launcher_background)
            }
        }

    }
}