package io.hwls.data.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(val session: Session) {
    @Serializable
    data class Session(
        val name: String,
        val key: String
    )
}