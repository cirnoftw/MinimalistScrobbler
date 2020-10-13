package io.hwls.domain.usecase.auth

import io.hwls.domain.common.CompletableUseCase
import io.hwls.domain.common.DispatcherProvider
import io.hwls.domain.gateway.AuthGateway
import kotlinx.coroutines.withContext

class LogoutUseCase(
    private val authGateway: AuthGateway,
    private val dispatcherProvider: DispatcherProvider
) : CompletableUseCase {
    override suspend fun invoke() = withContext(dispatcherProvider.io) {
        authGateway.logout()
    }
}