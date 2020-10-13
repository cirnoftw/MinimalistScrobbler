package io.hwls.domain.usecase.settings

import io.hwls.domain.common.DispatcherProvider
import io.hwls.domain.common.FlowUseCase
import io.hwls.domain.gateway.SettingsGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ObserveNowPlayingEnabledUseCase(
    private val settingsGateway: SettingsGateway,
    private val dispatcherProvider: DispatcherProvider
) : FlowUseCase<Boolean> {
    override fun invoke(): Flow<Boolean> = settingsGateway
        .observeNowPlayingEnabled()
        .flowOn(dispatcherProvider.io)
}