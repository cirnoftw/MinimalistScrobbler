package io.hwls.domain.usecase

import io.hwls.domain.common.FlowUseCase
import io.hwls.domain.common.ioDispatcher
import io.hwls.domain.gateway.AuthGateway
import io.hwls.domain.model.LoginState
import kotlinx.coroutines.flow.Flow

class ObserveLoginStateUseCase(private val authGateway: AuthGateway) : FlowUseCase<LoginState?> {
    override fun execute(): Flow<LoginState?> = authGateway
        .observeLoginState()
        .ioDispatcher()
}