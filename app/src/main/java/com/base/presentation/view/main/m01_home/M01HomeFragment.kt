package com.base.presentation.view.main.m01_home

import androidx.fragment.app.viewModels
import com.base.databinding.M01FragmentHomeBinding
import com.base.presentation.base.BaseFragment
import com.base.presentation.base.navigate
import com.base.presentation.view.main.M00MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01FragmentHomeBinding>(M01FragmentHomeBinding::inflate) {

    private val viewModel: M01HomeViewModel by viewModels()
    override fun initView() {
        binding.tv.setOnClickListener {
            navigate(M00MainFragmentDirections.actionM00MainFragmentToM06DemoFragment())
        }
        binding.swLayout.setOnRefreshListener {
            viewModel.updateData()
        }
    }

    override suspend fun initObserverCreated() {
        viewModel.homeUiState.collectLatest { state ->
            Timber.e(state.toString())
            when (state) {
                is HomeUiState.Error -> {
                    binding.swLayout.isRefreshing = false
                    binding.tv.text = state.msg
                }

                is HomeUiState.Loading -> {
                    binding.tv.text = "LOADING"
                }

                is HomeUiState.Success -> {
                    binding.tv.text = state.data.take(5).joinToString(separator = "\n")
                    binding.swLayout.isRefreshing = false
                }
            }
        }
    }
}