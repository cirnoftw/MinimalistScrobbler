package io.hwls.domain.usecase.auth

import io.hwls.domain.common.CompletableParamsUseCase
import io.hwls.domain.common.DispatcherProvider
import io.hwls.domain.gateway.AuthGateway
import kotlinx.coroutines.withContext

class AuthUseCase(
    private val authGateway: AuthGateway,
    private val dispatcherProvider: DispatcherProvider
) : CompletableParamsUseCase<AuthUseCase.Params> {
    override suspend fun invoke(params: Params) = withContext(dispatcherProvider.io) {
        authGateway.auth(params.login, params.password)
    }

    data class Params(val login: String, val password: String)
}