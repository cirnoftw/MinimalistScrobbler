package io.hwls.domain.common

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val unconfined: CoroutineDispatcher

    val default: CoroutineDispatcher

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher
}