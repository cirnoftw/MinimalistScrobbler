package io.hwls.android.di

import io.hwls.data.gateway.AuthGatewayImpl
import io.hwls.data.gateway.SettingsGatewayImpl
import io.hwls.domain.gateway.AuthGateway
import io.hwls.domain.gateway.SettingsGateway
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
internal val gateways = module {
    single<AuthGateway> { AuthGatewayImpl(get(), get()) }
    single<SettingsGateway> { SettingsGatewayImpl(get()) }
}