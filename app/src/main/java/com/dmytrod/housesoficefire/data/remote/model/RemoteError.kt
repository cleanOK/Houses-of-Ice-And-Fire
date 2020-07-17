package com.dmytrod.housesoficefire.data.remote.model

import androidx.annotation.StringRes
import com.dmytrod.housesoficefire.R

sealed class RemoteError(@StringRes val errorMessageRes: Int, val cause: Throwable) {
    data class NoInternet(val e: Throwable) : RemoteError(R.string.no_internet, e)
    data class ServerFailure(val e: Throwable) : RemoteError(R.string.server_error, e)
    data class Unexpected(val e: Throwable) : RemoteError(R.string.something_went_wrong, e)
}