package io.hwls.data.prefs

import com.tfcporciuncula.flow.FlowSharedPreferences
import com.tfcporciuncula.flow.Preference
import io.hwls.domain.model.LoginState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

private const val KEY_USER_NAME = "user_name"
private const val KEY_USER_TOKEN = "user_token"

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
}