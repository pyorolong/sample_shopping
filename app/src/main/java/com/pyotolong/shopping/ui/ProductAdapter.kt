package com.pyotolong.shopping.ui

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pyotolong.shopping.data.Product

class ProductAdapter(private val onItemClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>() {
    var productList: List<Product>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder.getViewHolder(parent, onItemClick)


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        productList?.get(position)?.also { holder.bind(it) }
    }

    override fun getItemCount() = productList?.size ?: 0

}

@BindingAdapter("productList")
fun bindProductList(recycler: RecyclerView, items: List<Product>?) {
    val adapter = recycler.adapter as ProductAdapter?
    adapter?.let {
        it.productList = items
        it.notifyDataSetChanged()
    }
}
