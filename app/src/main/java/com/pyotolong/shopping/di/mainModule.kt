package com.pyotolong.shopping.di

import com.pyotolong.shopping.ui.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainFragmentViewModel() }
}