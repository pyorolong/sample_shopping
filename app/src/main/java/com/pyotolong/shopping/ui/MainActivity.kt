package com.pyotolong.shopping.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.pyotolong.shopping.R
import com.pyotolong.shopping.common.Constants.CATEGORY_ALL
import com.pyotolong.shopping.data.ProductResponse
import com.pyotolong.shopping.databinding.ActivityMainBinding
import com.pyotolong.shopping.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        RetrofitBuilder.api.getCategories().enqueue(object : Callback<Array<String>> {
            override fun onResponse(
                call: Call<Array<String>>,
                response: Response<Array<String>>
            ) {
                response.body()?.let {
                    val categories = it.toMutableList()
                    categories.add(0, CATEGORY_ALL)

                    //prevent refreshing fragment on tab changed
                    binding.viewPager.offscreenPageLimit = categories.size
                    binding.viewPager.adapter =
                        ViewPagerAdapter(
                            supportFragmentManager,
                            lifecycle,
                            categories.toTypedArray()
                        )

                    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                        tab.text = categories[position]
                    }.attach()
                }

            }

            override fun onFailure(call: Call<Array<String>>, t: Throwable) {
                Log.e("", "error occurred : ${t.message}")
            }

        })
    }
}