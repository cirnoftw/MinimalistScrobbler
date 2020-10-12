package io.hwls.domain.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

fun <T> Flow<T>.ioDispatcher() = flowOn(Dispatchers.IO)