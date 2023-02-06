package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val binder: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {
    private var binding: VB? = null

    protected fun requireBinding(): VB {
        return checkNotNull(binding)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = binder.invoke(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        checkNetwork()
        setupObservers()
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    protected abstract fun setupUI()

    protected open fun setupObservers() {}

    protected open fun checkNetwork() {}

    fun <T : Any, N : Any> renderResult(
        root: ViewGroup, result: BaseResult<T, N>,
        onEmpty: () -> Unit,
        onPending: () -> Unit,
        onError: (N) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        root.children.forEach { it.isVisible = false }
        when (result) {
            is Empty -> onEmpty()
            is PendingResult -> onPending()
            is SuccessResult -> onSuccess(result.data)
            is ErrorResult -> onError(result.errorMsg)
        }
    }

}