package io.hwls.data.gateway

import io.hwls.data.network.LfmApi
import io.hwls.data.prefs.PrefsWrapper
import io.hwls.domain.API_KEY
import io.hwls.domain.API_SECRET
import io.hwls.domain.gateway.AuthGateway
import io.hwls.domain.model.LoginState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.security.MessageDigest

@ExperimentalCoroutinesApi
class AuthGatewayImpl(
    private val lfmApi: LfmApi,
    private val prefsWrapper: PrefsWrapper
) : AuthGateway {
    override fun observeLoginState(): Flow<LoginState?> = prefsWrapper.loginStateFlow

    override suspend fun auth(login: String, password: String) {
        val md5 = MessageDigest.getInstance("MD5")
        val params = mapOf(
            "api_key" to API_KEY,
            "method" to "auth.getMobileSession",
            "password" to password,
            "username" to login
        )
        val signature = params
            .map { it.key + it.value }
            .reduce(String::plus)
            .plus(API_SECRET)
            .let { md5.digest(it.encodeToByteArray()) }
            .let { it.joinToString("") { s -> "%02x".format(s) } }

        val session = lfmApi.getToken(
            username = login,
            password = password,
            signature = signature
        ).session

        prefsWrapper.userToken.setAndCommit(session.key)
        prefsWrapper.userName.setAndCommit(session.name)
    }
}