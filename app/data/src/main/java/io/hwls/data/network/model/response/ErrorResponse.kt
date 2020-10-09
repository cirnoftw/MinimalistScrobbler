package io.hwls.data.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: Int,
    val message: String
)