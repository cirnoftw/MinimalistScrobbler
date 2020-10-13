package io.hwls.domain.gateway

import kotlinx.coroutines.flow.Flow

interface SettingsGateway {
    fun osberveScrobblingEnabled(): Flow<Boolean>

    fun observeNowPlayingEnabled(): Flow<Boolean>

    fun observeScrobblingPoint(): Flow<Int>

    fun observeScrobbleShort(): Flow<Boolean>

    suspend fun setScrobbling(isEnabled: Boolean)

    suspend fun setNowPlayingEnabled(isEnabled: Boolean)

    suspend fun setScrobblingPoint(percent: Int)

    suspend fun setScrobbleShort(isEnabled: Boolean)
}