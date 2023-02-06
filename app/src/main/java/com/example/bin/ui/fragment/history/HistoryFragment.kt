package com.example.bin.ui.fragment.history

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.bin.databinding.FragmentHistoryBinding
import com.example.bin.ui.fragment.history.adapter.HistoryAdapter
import com.example.core.base.BaseFragment
import com.example.domain.bin.entity.BinHistory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {
    private val historyAdapter = HistoryAdapter()

    private val viewModel: HistoryViewModel by viewModels()
    override fun setupUI() {
        initAdapter()
    }

    override fun setupObservers() {
        super.setupObservers()
        observeBin()
    }

    private fun observeBin() {
        viewModel.getBin().flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { handleBin(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleBin(list: List<BinHistory>) {
        historyAdapter.list = list
    }

    private fun initAdapter() {
        requireBinding().rvHistory.adapter = historyAdapter
    }

}