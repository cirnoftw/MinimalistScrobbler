package io.hwls.domain.usecase.auth

import io.hwls.domain.common.DispatcherProvider
import io.hwls.domain.common.FlowUseCase
import io.hwls.domain.gateway.AuthGateway
import io.hwls.domain.model.LoginState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ObserveLoginStateUseCase(
    private val authGateway: AuthGateway,
    private val dispatcherProvider: DispatcherProvider
) : FlowUseCase<LoginState?> {
    override fun invoke(): Flow<LoginState?> = authGateway
        .observeLoginState()
        .flowOn(dispatcherProvider.io)
}