package io.hwls.android.di

import io.hwls.data.gateway.AuthGatewayImpl
import io.hwls.domain.gateway.AuthGateway
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
internal val gateways = module {
    single<AuthGateway> { AuthGatewayImpl(get(), get()) }
}