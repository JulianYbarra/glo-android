package com.junka.glo.ui.product

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.junka.glo.R
import com.junka.glo.databinding.FragmentProductDetailBinding
import com.junka.glo.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding : FragmentProductDetailBinding
    private val viewModel by viewModels<ProductDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailBinding.bind(view)
        binding.setUi()

        launchAndCollect(viewModel.state){ setUiState(it) }
    }

    private fun FragmentProductDetailBinding.setUi(){
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setUiState(uiState: ProductDetailViewModel.UiState) = with(binding){
        progress.isVisible = uiState.loading
        uiState.product?.let {
            toolbar.title = it.title
            descriptionTv.text = it.description
            imageIv.load(it.image){
                crossfade(true)
                error(R.drawable.ic_launcher_background)
            }
        }
        uiState.error?.let { error -> Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show() }
    }
}