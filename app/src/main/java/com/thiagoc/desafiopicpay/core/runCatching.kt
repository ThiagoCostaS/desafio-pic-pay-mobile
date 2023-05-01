package com.thiagoc.desafiopicpay.core

import timber.log.Timber

suspend fun <T, R> T.runCatching(
    execute: suspend () -> R,
    onSuccess: (R) -> Unit = {},
    onFailure: (Throwable) -> Unit = {}
) {
    runCatching {
        execute()
    }.onSuccess(onSuccess)
        .onFailure {
            Timber.e(it)
            onFailure(it)
        }
}