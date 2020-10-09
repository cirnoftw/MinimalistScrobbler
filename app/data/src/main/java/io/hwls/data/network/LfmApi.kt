package io.hwls.data.network

import io.hwls.data.network.model.response.TokenResponse
import retrofit2.http.POST

interface LfmApi {
    @POST("?method=auth.gettoken")
    suspend fun getToken(): TokenResponse
}