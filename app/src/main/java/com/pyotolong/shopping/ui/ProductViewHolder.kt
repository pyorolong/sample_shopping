package com.pyotolong.shopping.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pyotolong.shopping.R
import com.pyotolong.shopping.data.Product
import com.pyotolong.shopping.databinding.ItemProductBinding


class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.product = product
        binding.root.setOnClickListener { onItemClick(product) }
    }

    companion object {
        fun getViewHolder(
            parent: ViewGroup,
            itemClick: (Product) -> Unit
        ): ProductViewHolder {
            val binding =
                ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProductViewHolder(binding, itemClick)
        }

        @JvmStatic
        @BindingAdapter("product_image")
        fun setProductImage(imageView: ImageView, thumbnailUrl: String?) {
            if (!thumbnailUrl.isNullOrBlank()) {
                Glide.with(imageView.context).load(thumbnailUrl).centerCrop().into(imageView)
            } else {
                imageView.setBackgroundResource(R.drawable.cute_cat)
            }
        }
    }

}