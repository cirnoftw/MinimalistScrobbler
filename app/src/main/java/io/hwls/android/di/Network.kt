package io.hwls.android.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.hwls.data.BuildConfig
import io.hwls.data.network.LfmApi
import io.hwls.data.network.interceptor.ApiQueryInterceptor
import io.hwls.data.network.interceptor.ErrorInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@ExperimentalSerializationApi
internal val network = module {
    val contentType = "application/json".toMediaType()

    single {
        OkHttpClient().newBuilder()
            .addNetworkInterceptor(ApiQueryInterceptor())
            .addNetworkInterceptor(ErrorInterceptor(get()))
            .callTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        val json = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(get())
            .build()
    }

    single<LfmApi> {
        get<Retrofit>().create(LfmApi::class.java)
    }
}