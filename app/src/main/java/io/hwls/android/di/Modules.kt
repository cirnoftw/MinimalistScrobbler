package io.hwls.android.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.serialization.ExperimentalSerializationApi

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
val appModules = listOf(
    appModule,
    viewModels,
    navigation,
    network,
    gateways,
    interactors
)