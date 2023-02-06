package com.example.bin.ui.fragment.home

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bin.R
import com.example.bin.databinding.FragmentHomeBinding
import com.example.bin.util.ext.observeEvent
import com.example.bin.util.ext.onTryAgainListener
import com.example.bin.util.ext.renderSimpleResult
import com.example.bin.util.ext.showToast
import com.example.bin.util.network.ConnectivityObserver
import com.example.bin.util.network.NetworkConnectivityObserver
import com.example.core.base.BaseFragment
import com.example.core.base.BaseResult
import com.example.domain.bin.entity.BinEntity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val networkConnectivityObserver by lazy {
        NetworkConnectivityObserver(requireContext())
    }
    private val viewModel: HomeViewModel by viewModels()

    private var isConnection = false
    private var status = false

    override fun setupUI() {
        initBtn()
        initEditActionClick()
    }

    private fun initEditActionClick() {
        requireBinding().editBin.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getBin(requireBinding().editBin.text.toString())
                return@setOnEditorActionListener false
            }
            return@setOnEditorActionListener false
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        observeNetwork()
        observeBinResult()
        observeError()
    }

    private fun observeError() {
        viewModel.errorEvents.observeEvent(viewLifecycleOwner) { msg ->
            requireContext().showToast(msg)
        }
    }

    private fun observeBinResult() {
        viewModel.binResult.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { handleBinResult(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleBinResult(result: BaseResult<BinEntity, String>) {
        renderSimpleResult(
            requireBinding().root,
            result,
            onSuccess = { data ->
                handleBin(data)
            },
            onError = { msg ->
                viewModel.setError(msg)
            }
        )
    }

    private fun handleBin(data: BinEntity) {
        with(requireBinding()) {
            txtBankName.text = data.bank.name
            txtBankUrl.text = data.bank.url
            txtBankNumber.text = data.bank.phone
            txtSchemeNetwork.text = data.scheme
            txtBrand.text = data.brand
            txtType.text = data.type
            txtPrepaid.text = data.prepaid
            txtNumberLength.text = data.number.length
            txtNumberLuhn.text = data.number.luh
            txtCountry.text = data.country.emojiName
            txtLatitudeLongitude.text = requireContext().getString(
                R.string.latitude_longitude,
                data.country.latitude,
                data.country.longitude
            )
        }
        initBtnWithBin(data)
    }

    private fun initBtnWithBin(data: BinEntity) {
        requireBinding().txtBankNumber.setOnClickListener {
            callNumber(data.bank.phone, data.bank.isPhone)
        }
        requireBinding().txtBankUrl.setOnClickListener {
            openUrl(data.bank.url, data.bank.isUrl)
        }
        requireBinding().txtLatitudeLongitude.setOnClickListener {
            openMap(data.country.latitude, data.country.longitude, data.country.isCoordinate)
        }
    }

    private fun observeNetwork() {
        networkConnectivityObserver.observe()
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { handleNetwork(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleNetwork(status: ConnectivityObserver.Status) {
        isConnection = when (status) {
            ConnectivityObserver.Status.Available -> {
                true
            }
            ConnectivityObserver.Status.Unavailable -> {
                false
            }
            ConnectivityObserver.Status.Losing -> {
                false
            }
            ConnectivityObserver.Status.Lost -> {
                false
            }
        }
        setConnection(isConnection)
    }

    override fun checkNetwork() {
        super.checkNetwork()
        isConnection = networkConnectivityObserver.isInternetAvailable()
        setConnection(isConnection)
    }

    private fun showNotConnection() {
        Snackbar.make(
            requireBinding().root,
            requireContext().getString(R.string.not_network),
            Snackbar.LENGTH_LONG
        ).setAction(requireContext().getString(R.string.try_again)) {
            checkNetwork()
        }.show()
    }

    private fun setConnection(connection: Boolean) {
        viewModel.setNetworkState(connection)
        if (!connection) status = true
        if (status) {
            if (!connection)
                showNotConnection()
            else {
                showConnectionRestored()
                status = false
            }
        }
    }

    private fun showConnectionRestored() {
        Snackbar.make(
            requireBinding().root,
            requireContext().getString(R.string.connection_restored),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun initBtn() {
        requireBinding().imageHistory.setOnClickListener {
            navigateHistory()
        }
        requireBinding().imageClear.setOnClickListener {
            clearEdit()
        }
        requireBinding().imageSearch.setOnClickListener {
            viewModel.getBin(requireBinding().editBin.text.toString())
            hideKeyboard()
        }
        onTryAgainListener(requireBinding().root) {
            viewModel.getBin(requireBinding().editBin.text.toString())
        }
    }

    private fun navigateHistory() {
        val action = HomeFragmentDirections.actionHomeFragmentToHistoryFragment()
        findNavController().navigate(action)
    }

    private fun clearEdit() {
        requireBinding().editBin.setText("")
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun openUrl(url: String, isUrl: Boolean) {
        if (isUrl) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("http://$url")
            startActivity(i)
        } else
            viewModel.setError(requireContext().getString(R.string.no_data))
    }

    private fun callNumber(number: String, isNumber: Boolean) {
        if (isNumber) {
            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:$number")
            startActivity(i)
        } else
            viewModel.setError(requireContext().getString(R.string.no_data))
    }

    private fun openMap(latitude: String, longitude: String, isMap: Boolean) {
        if (isMap) {
            val uri =
                "geo:$latitude,$longitude"
            val i = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(uri)
            }
            startActivity(i)
        } else
            viewModel.setError(requireContext().getString(R.string.no_data))
    }
}