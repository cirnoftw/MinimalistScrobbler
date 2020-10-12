package io.hwls.data.network.interceptor

import io.hwls.data.common.appendUrlEncoded
import io.hwls.domain.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_CONTENT_LENGTH = "Content-Length"
private const val QUERY_API_KEY = "api_key"
private const val QUERY_FORMAT = "format"

class ApiQueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        var body = request.body
        when (request.method) {
            "GET" -> {
                val url = chain.request().url.newBuilder()
                    .addQueryParameter(QUERY_API_KEY, API_KEY)
                    .addQueryParameter(QUERY_FORMAT, "json")
                    .build()
                requestBuilder.url(url)
            }
            "POST" -> {
                body = body
                    ?.appendUrlEncoded(QUERY_API_KEY to API_KEY)
                    ?.appendUrlEncoded(QUERY_FORMAT to "json")
                requestBuilder.post(body!!)
            }
            else -> {
            }
        }
        val newRequest = requestBuilder
            .removeHeader(HEADER_CONTENT_LENGTH)
            .addHeader(HEADER_CONTENT_LENGTH, body?.contentLength().toString())
            .build()
        return chain.proceed(newRequest)
    }
}