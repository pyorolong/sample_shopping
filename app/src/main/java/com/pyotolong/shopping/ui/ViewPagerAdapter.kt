package com.pyotolong.shopping.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var categoryList: Array<String>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = categoryList.size

    override fun createFragment(position: Int) = MainFragment.newInstance(Bundle().apply {
        putString(
            MainFragment.CATEGORY,
            categoryList[position]
        )
    })
}