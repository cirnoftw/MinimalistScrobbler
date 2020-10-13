package io.hwls.data.prefs

import com.tfcporciuncula.flow.FlowSharedPreferences
import com.tfcporciuncula.flow.Preference
import io.hwls.domain.model.LoginState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

private const val KEY_USER_NAME = "user_name"
private const val KEY_USER_TOKEN = "user_token"
private const val KEY_SCROBBLE = "scrobble"
private const val KEY_NOWPLAYING = "nowplaying"
private const val KEY_SCROBBLE_POINT = "scrobble_point"
private const val KEY_SEND_SHORT = "send_short"

@ExperimentalCoroutinesApi
class PrefsWrapper(private val prefs: FlowSharedPreferences) {
    val userName: Preference<String?>
        get() = prefs.getNullableString(KEY_USER_NAME)

    val userToken: Preference<String?>
        get() = prefs.getNullableString(KEY_USER_TOKEN)

    val loginStateFlow: Flow<LoginState?> = userName.asFlow()
        .combine(userToken.asFlow()) { username, token ->
            if (username != null && token != null)
                LoginState(username, token)
            else null
        }

    val scrobble: Preference<Boolean>
        get() = prefs.getBoolean(KEY_SCROBBLE)

    val sendNowPlaying: Preference<Boolean>
        get() = prefs.getBoolean(KEY_NOWPLAYING)

    val scrobblePoint: Preference<Int>
        get() = prefs.getInt(KEY_SCROBBLE_POINT, 50)

    val sendShortEnabled: Preference<Boolean>
        get() = prefs.getBoolean(KEY_SEND_SHORT)
}