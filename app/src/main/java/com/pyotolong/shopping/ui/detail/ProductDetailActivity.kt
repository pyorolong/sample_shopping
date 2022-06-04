package com.pyotolong.shopping.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pyotolong.shopping.R
import com.pyotolong.shopping.common.Constants.KEY_PRODUCT
import com.pyotolong.shopping.data.Product
import com.pyotolong.shopping.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let {
            binding.product = it

            it.images.forEach { img ->
                binding.linImg.addView(makeImageView(img))
            }
        }
    }

    private fun makeImageView(image: String) =
        LayoutInflater.from(this).inflate(R.layout.view_image, null).apply {
            Glide.with(this).load(image).fitCenter().into(findViewById(R.id.image))
        }


    fun finish(v: View) {
        finish()
    }

    companion object {
        fun startProductDetailActivity(context: Context, product: Product) {
            context.startActivity(Intent(context, ProductDetailActivity::class.java).apply {
                addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
                putExtra(KEY_PRODUCT, product)
            })
        }
    }

}