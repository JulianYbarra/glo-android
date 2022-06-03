package com.junka.glo.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.junka.glo.R
import com.junka.glo.databinding.FragmentHomeBinding
import com.junka.glo.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    private val productAdapter by lazy { ProductAdapter(){} }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        binding.setUi()

        launchAndCollect(viewModel.state){ setUiState(it) }

    }

    private fun FragmentHomeBinding.setUi(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    private fun setUiState(uiState: HomeViewModel.UiState) = with(binding){
        progress.isVisible = uiState.loading
        uiState.products?.let { productAdapter.submitList(it) }
        uiState.error?.let { Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show() }
    }
}