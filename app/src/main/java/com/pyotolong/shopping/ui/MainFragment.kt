package com.pyotolong.shopping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pyotolong.shopping.databinding.FragmentMainBinding
import com.pyotolong.shopping.ui.custom.SpaceItemDecorator
import com.pyotolong.shopping.ui.detail.ProductDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModel()
    private val adapter: ProductAdapter by lazy {
        ProductAdapter {
            ProductDetailActivity.startProductDetailActivity(requireContext(), it)
            Toast.makeText(requireContext(), "${it.id}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(CATEGORY)?.let {
            viewModel.getProductsList(it)
        }
    }

    fun setupRecyclerView() {
        binding.recycler.addItemDecoration(
            SpaceItemDecorator(
                left = 15,
                top = 15,
                right = 15,
                bottom = 15
            )
        )
        binding.recycler.adapter = adapter
    }


    companion object {
        const val CATEGORY: String = ""
        fun newInstance(bundle: Bundle?): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}