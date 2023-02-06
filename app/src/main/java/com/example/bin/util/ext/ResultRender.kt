package com.example.bin.util.ext

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.example.bin.databinding.PartResultBinding
import com.example.bin.R
import com.example.core.base.BaseFragment
import com.example.core.base.BaseResult

fun <B : ViewBinding, T : Any,
        N : Any> BaseFragment<B>.renderSimpleResult(
    root: ViewGroup,
    result: BaseResult<T, N>, onSuccess: (T) -> Unit,
    onError: (N) -> Unit
) {
    val binding = PartResultBinding.bind(root)
    renderResult(
        root = root,
        result = result,
        onEmpty = {
            root.children.filter {
                it.id != R.id.progress_bar && it.id != R.id.error_cont
                        && it.id != R.id.group_info_container && it.id != R.id.container_bank &&
                        it.id != R.id.container_brand && it.id != R.id.container_country
                        && it.id != R.id.container_flow && it.id != R.id.container_type
                        && it.id != R.id.container_number && it.id != R.id.container_scheme
                        && it.id != R.id.container_prepaid

            }.forEach { it.visible() }
        },
        onPending = {
            binding.progressBar.visible()
        },
        onError = { error ->
            binding.errorCont.visible()
            onError(error)
        },
        onSuccess = { data ->
            root.children
                .filter { it.id != R.id.progress_bar && it.id != R.id.error_cont }
                .forEach { it.isVisible = true }
            onSuccess(data)
        }
    )
}

fun onTryAgainListener(
    root: View,
    onTryAgainAction: () -> Unit,
) {
    root.findViewById<Button>(R.id.btn_try_again).setOnClickListener { onTryAgainAction() }
}