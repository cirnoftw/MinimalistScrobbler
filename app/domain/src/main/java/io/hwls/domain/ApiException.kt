package io.hwls.domain

class ApiException(
    val code: Int,
    override val message: String
) : Exception()