package io.hwls.android.di

import io.hwls.domain.usecase.AuthUseCase
import io.hwls.domain.usecase.ObserveLoginStateUseCase
import org.koin.dsl.module

internal val interactors = module {
    single { AuthUseCase(get()) }
    single { ObserveLoginStateUseCase(get()) }
}