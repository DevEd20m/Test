package com.faztbit.alwaopportunity.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.faztbit.alwaopportunity.R
import com.faztbit.alwaopportunity.databinding.FragmentMainBinding
import com.faztbit.alwaopportunity.domain.utils.ViewState
import com.faztbit.alwaopportunity.features.utils.collectWhenResumed
import com.faztbit.alwaopportunity.features.utils.handleErrorBase
import com.faztbit.alwaopportunity.features.utils.safeNavigate
import com.faztbit.alwaopportunity.features.utils.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModel<MainViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        events()
    }

    private fun events() {
        binding.buttonAdd.setOnClickListener {
            findNavController().safeNavigate(MainFragmentDirections.actionFirstFragmentToSecondFragment())
        }

    }

    private fun setUpObservers() {
        collectWhenResumed(viewModel._machineEvent) {
            binding.progressBar.isVisible = it == ViewState.Loading
            when (it) {
                is ViewState.Error -> handleErrorBase(throwable = it) {

                }

                is ViewState.Success -> {

                }

                else -> {

                }
            }
        }
    }
}