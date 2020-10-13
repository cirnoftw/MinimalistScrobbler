package io.hwls.android.di

import io.hwls.domain.usecase.auth.AuthUseCase
import io.hwls.domain.usecase.auth.LogoutUseCase
import io.hwls.domain.usecase.auth.ObserveLoginStateUseCase
import io.hwls.domain.usecase.settings.*
import org.koin.dsl.module

internal val interactors = module {
    single { AuthUseCase(get(), get()) }
    single { ObserveLoginStateUseCase(get(), get()) }
    single { LogoutUseCase(get(), get()) }

    single { ObserveScrobblingEnabledUseCase(get(), get()) }
    single { ObserveNowPlayingEnabledUseCase(get(), get()) }
    single { ObserveScrobblingPointUseCase(get(), get()) }
    single { ObserveScrobbleShortUseCase(get(), get()) }
    single { SetScrobblingEnabledUseCase(get(), get()) }
    single { SetNowPlayingEnabledUseCase(get(), get()) }
    single { SetScrobblingPointUseCase(get(), get()) }
    single { SetScrobbleShortUseCase(get(), get()) }
}