package io.hwls.data.network.interceptor

import io.hwls.data.common.NetErrors.UNAUTHORIZED
import io.hwls.data.prefs.PrefsWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

@ExperimentalCoroutinesApi
class ErrorInterceptor(private val prefs: PrefsWrapper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request()) // todo
        if (response.code == UNAUTHORIZED) {
            runBlocking(Dispatchers.IO) {
                prefs.userName.deleteAndCommit()
                prefs.userToken.deleteAndCommit()
            }
        }
        return response
    }
}