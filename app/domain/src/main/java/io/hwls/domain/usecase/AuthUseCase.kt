package io.hwls.domain.usecase

import io.hwls.domain.common.CompletableUseCase
import io.hwls.domain.gateway.AuthGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthUseCase(private val authGateway: AuthGateway) : CompletableUseCase<AuthUseCase.Params> {
    override suspend fun execute(params: Params) = withContext(Dispatchers.IO) {
        authGateway.auth(params.login, params.password)
    }

    data class Params(val login: String, val password: String)
}