package io.hwls.android.di

import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
val appModules = listOf(
    appModule,
    androidModule,
    viewModels,
    navigation,
    network,
    gateways,
    interactors
)