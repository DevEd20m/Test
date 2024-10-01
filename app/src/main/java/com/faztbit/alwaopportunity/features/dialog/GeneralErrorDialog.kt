package com.faztbit.alwaopportunity.features.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.faztbit.alwaopportunity.R
import com.faztbit.alwaopportunity.databinding.DialogGeneralErrorBinding

class GeneralErrorDialog(
    private val retry: (() -> Unit)? = null,
    private val message: String? = null,
) : DialogFragment(R.layout.dialog_general_error) {
    private val binding by viewBinding(DialogGeneralErrorBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        events()
        setUpViews()
    }

    private fun setUpViews() {
        message?.let {
            binding.textViewDescription.text = it
        }
    }

    private fun events() {
        with(binding) {
            buttonContinue.setOnClickListener {
                dismiss()
                retry?.invoke()
            }
        }
    }

    companion object {
        fun newInstance(retry: () -> Unit, message: String?) =
            GeneralErrorDialog(retry = retry, message = message)

        val TAG = GeneralErrorDialog::class.simpleName.toString()
    }
}