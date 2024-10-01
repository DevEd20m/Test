package com.faztbit.alwaopportunity.domain.utils

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val exception: String) : ViewState<Nothing>()
    object Empty : ViewState<Nothing>()
    data class Exceptional(val message: String) : ViewState<Nothing>()
}