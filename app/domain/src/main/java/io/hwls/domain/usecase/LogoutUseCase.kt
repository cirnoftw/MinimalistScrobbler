package io.hwls.domain.usecase

import io.hwls.domain.common.CompletableUseCase
import io.hwls.domain.gateway.AuthGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogoutUseCase(private val authGateway: AuthGateway) : CompletableUseCase {
    override suspend fun execute() = withContext(Dispatchers.IO) {
        authGateway.logout()
    }
}