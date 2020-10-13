package io.hwls.domain.usecase.settings

import io.hwls.domain.common.CompletableParamsUseCase
import io.hwls.domain.common.DispatcherProvider
import io.hwls.domain.gateway.SettingsGateway
import kotlinx.coroutines.withContext

class SetScrobblingPointUseCase(
    private val settingsGateway: SettingsGateway,
    private val dispatcherProvider: DispatcherProvider
) : CompletableParamsUseCase<Int> {
    override suspend fun invoke(params: Int) = withContext(dispatcherProvider.io) {
        settingsGateway
            .setScrobblingPoint(percent = params)
    }
}