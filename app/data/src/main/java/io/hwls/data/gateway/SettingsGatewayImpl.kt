package io.hwls.data.gateway

import io.hwls.data.prefs.PrefsWrapper
import io.hwls.domain.gateway.SettingsGateway
import kotlinx.coroutines.flow.Flow

class SettingsGatewayImpl(private val prefsWrapper: PrefsWrapper) : SettingsGateway {
    override fun osberveScrobblingEnabled(): Flow<Boolean> = prefsWrapper.scrobble.asFlow()

    override fun observeNowPlayingEnabled(): Flow<Boolean> = prefsWrapper.sendNowPlaying.asFlow()

    override fun observeScrobblingPoint(): Flow<Int> = prefsWrapper.scrobblePoint.asFlow()

    override fun observeScrobbleShort(): Flow<Boolean> = prefsWrapper.sendShortEnabled.asFlow()

    override suspend fun setScrobbling(isEnabled: Boolean) = prefsWrapper.scrobble.set(isEnabled)

    override suspend fun setNowPlayingEnabled(isEnabled: Boolean) =
        prefsWrapper.sendNowPlaying.set(isEnabled)

    override suspend fun setScrobblingPoint(percent: Int) = prefsWrapper.scrobblePoint.set(percent)

    override suspend fun setScrobbleShort(isEnabled: Boolean) =
        prefsWrapper.sendShortEnabled.set(isEnabled)
}