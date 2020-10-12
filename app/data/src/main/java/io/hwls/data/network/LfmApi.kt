package io.hwls.data.network

import io.hwls.data.network.model.response.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LfmApi {
    @POST("2.0/")
    @FormUrlEncoded
    suspend fun getToken(
        @Field("method") method: String = "auth.getMobileSession",
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("api_sig") signature: String
    ): TokenResponse
}