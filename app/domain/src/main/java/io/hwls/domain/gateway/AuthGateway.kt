package io.hwls.domain.gateway

import io.hwls.domain.model.LoginState
import kotlinx.coroutines.flow.Flow

interface AuthGateway {
    fun observeLoginState(): Flow<LoginState?>

    suspend fun auth(login: String, password: String)
}