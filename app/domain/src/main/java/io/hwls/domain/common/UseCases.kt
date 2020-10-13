package io.hwls.domain.common

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T> {
    operator fun invoke(): Flow<T>
}

interface FlowParamsUseCase<R, T> {
    operator fun invoke(params: R): Flow<T>
}

interface SingleUseCase<T> {
    suspend operator fun invoke(): T
}

interface SingleParamsUseCase<R, T> {
    suspend operator fun invoke(params: R): T
}

interface CompletableUseCase {
    suspend operator fun invoke()
}

interface CompletableParamsUseCase<R> {
    suspend operator fun invoke(params: R)
}