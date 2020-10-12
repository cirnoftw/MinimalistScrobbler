package io.hwls.domain.common

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T> {
    fun execute(): Flow<T>
}

interface SingleUseCase<R, T> {
    suspend fun execute(params: R): T
}

interface CompletableUseCase<R> {
    suspend fun execute(params: R)
}