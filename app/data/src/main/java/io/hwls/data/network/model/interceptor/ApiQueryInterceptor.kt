package io.hwls.data.network.model.interceptor

import io.hwls.resources.API_KEY
import io.hwls.resources.API_SIGNATURE
import okhttp3.Interceptor
import okhttp3.Response

private const val QUERY_API_KEY = "api_key"
private const val QUERY_API_SIGNATURE = "api_sig"
private const val QUERY_FORMAT = "format"

class ApiQueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .addQueryParameter(QUERY_API_SIGNATURE, API_SIGNATURE)
            .addQueryParameter(QUERY_FORMAT, "json")
            .build()
        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}