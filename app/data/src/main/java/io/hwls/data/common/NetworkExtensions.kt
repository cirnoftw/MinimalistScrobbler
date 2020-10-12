package io.hwls.data.common

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun RequestBody.appendUrlEncoded(field: Pair<String, String>) = let {
    it.bodyToString()
        .split("&")
        .plus(field.toList().joinToString("="))
        .joinToString("&")
        .toRequestBody("application/x-www-form-urlencoded".toMediaType())
}

fun RequestBody?.bodyToString(): String {
    if (this == null) return ""
    val buffer = okio.Buffer()
    writeTo(buffer)
    return buffer.readUtf8()
}