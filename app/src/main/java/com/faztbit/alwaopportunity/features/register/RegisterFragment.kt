package com.faztbit.alwaopportunity.features.register

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.faztbit.alwaopportunity.R
import com.faztbit.alwaopportunity.databinding.FragmentRegisterBinding
import com.faztbit.alwaopportunity.domain.utils.ViewState
import com.faztbit.alwaopportunity.features.utils.collectWhenResumed
import com.faztbit.alwaopportunity.features.utils.handleErrorBase
import com.faztbit.alwaopportunity.features.utils.setDataToDropdown
import com.faztbit.alwaopportunity.features.utils.showDatePicker
import com.faztbit.alwaopportunity.features.utils.viewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val viewModel by viewModel<RegisterViewModel>()
    private val binding by viewBinding(FragmentRegisterBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        events()
        setUpObservers()
    }

    private fun initViews() {
        with(binding) {
            textInputEditTextLastUsed.showDatePicker(1, 0)

            textInputEditTextLastUsed.addTextChangedListener {
                viewModel.lastUpdated.value = it?.trim().toString()
            }
            textInputEditTextName.addTextChangedListener {
                viewModel.description.value = it?.trim().toString()
            }
            textInputEditTextImportance.addTextChangedListener {
                viewModel.priority.value = it?.trim().toString()
            }

        }
    }

    private fun events() {
        binding.buttonSave.setOnClickListener {
            viewModel.registerMachine(
                binding.textInputEditTextName.text.toString().trim(),
                binding.textInputEditTextLastUsed.text.toString().trim(),
                binding.textInputEditTextImportance.text.toString().trim()
            )
        }
    }

    private fun setUpObservers() {
        collectWhenResumed(viewModel.statusButton) {
            binding.buttonSave.isEnabled = it
        }
        collectWhenResumed(viewModel._prioritiesEvent) {
            binding.textInputEditTextImportance.setDataToDropdown(it)
        }
        collectWhenResumed(viewModel._registerEvent) {
            when (it) {
                is ViewState.Error -> handleErrorBase(throwable = it) {

                }

                is ViewState.Success -> {
                    findNavController().popBackStack()
                }

                else -> {

                }
            }
        }
    }
}