package com.faztbit.alwaopportunity.features.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.alwaopportunity.domain.utils.ViewState
import com.faztbit.alwaopportunity.features.dialog.GeneralErrorDialog
import org.koin.android.ext.android.getKoinScope
import org.koin.androidx.viewmodel.ViewModelStoreOwnerProducer
import org.koin.androidx.viewmodel.ext.android.getViewModelFactory
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.properties.Delegates

fun <T> Fragment.collectWhenResumed(state: MutableLiveData<T>, onCollect: (T) -> Unit) {
    state.observe(viewLifecycleOwner) {
        onCollect.invoke(it)
    }
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) = Delegates.observable(initialValue) { _, old, new ->
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(old[oldItemPosition], new[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areContentsTheSame(old[oldItemPosition], new[newItemPosition])

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size
    }).dispatchUpdatesTo(this@basicDiffUtil)
}



fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(direction)
    }
}

fun NavController.safeNavigateAndClean(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(
            direction,
            NavOptions.Builder().setPopUpTo(direction.actionId, true, false).build()
        )
    }

}

@OptIn(KoinInternalApi::class)
inline fun <reified T : ViewModel> Fragment.viewModel(
    qualifier: Qualifier? = null,
    noinline owner: ViewModelStoreOwnerProducer = { this },
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    return viewModels(ownerProducer = owner) {
        getViewModelFactory<T>(owner(), qualifier, parameters, scope = getKoinScope())
    }
}

fun Fragment.handleErrorBase(
    throwable: ViewState.Error,
    actionRetry: (() -> Unit)? = null
) {
    showGeneralError(
        message = throwable.exception,
        actionRetry = actionRetry,
    )
}

fun Fragment.showGeneralError(
    message: String? = null,
    actionRetry: (() -> Unit)? = null,
) {

    if (parentFragmentManager.fragments.firstOrNull { it.tag == GeneralErrorDialog.TAG } == null) {
        GeneralErrorDialog.newInstance(
            retry = { actionRetry?.invoke() },
            message = message,
        ).show(parentFragmentManager, GeneralErrorDialog.TAG)
    }
}

