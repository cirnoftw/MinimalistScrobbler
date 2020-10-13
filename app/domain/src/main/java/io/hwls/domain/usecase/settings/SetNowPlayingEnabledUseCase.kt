package io.hwls.domain.usecase.settings

import io.hwls.domain.common.CompletableParamsUseCase
import io.hwls.domain.common.DispatcherProvider
import io.hwls.domain.gateway.SettingsGateway
import kotlinx.coroutines.withContext

class SetNowPlayingEnabledUseCase(
    private val settingsGateway: SettingsGateway,
    private val dispatcherProvider: DispatcherProvider
) : CompletableParamsUseCase<Boolean> {
    override suspend fun invoke(params: Boolean) = withContext(dispatcherProvider.io) {
        settingsGateway
            .setNowPlayingEnabled(isEnabled = params)
    }
}