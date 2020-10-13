package io.hwls.android.feature.settings

import androidx.lifecycle.*
import io.hwls.android.common.LoadingState
import io.hwls.android.common.MessageBus
import io.hwls.domain.model.LoginState
import io.hwls.domain.usecase.auth.AuthUseCase
import io.hwls.domain.usecase.auth.LogoutUseCase
import io.hwls.domain.usecase.auth.ObserveLoginStateUseCase
import io.hwls.domain.usecase.settings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
class SettingsViewModel(
    private val authUseCase: AuthUseCase,
    private val observeLoginStateUseCase: ObserveLoginStateUseCase,
    private val observeScrobblingEnabledUseCase: ObserveScrobblingEnabledUseCase,
    private val observeNowPlayingEnabledUseCase: ObserveNowPlayingEnabledUseCase,
    private val observeScrobblingPointUseCase: ObserveScrobblingPointUseCase,
    private val observeScrobbleShortUseCase: ObserveScrobbleShortUseCase,
    private val setScrobblingEnabledUseCase: SetScrobblingEnabledUseCase,
    private val setNowPlayingEnabledUseCase: SetNowPlayingEnabledUseCase,
    private val setScrobblingPointUseCase: SetScrobblingPointUseCase,
    private val setScrobbleShortUseCase: SetScrobbleShortUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val messageBus: MessageBus
) : ViewModel() {
    val loginState: LiveData<LoginState?>
        get() = observeLoginStateUseCase().asLiveData()

    val scrobblingEnabled: LiveData<Boolean>
        get() = observeScrobblingEnabledUseCase().asLiveData()

    val nowPlayingEnabled: LiveData<Boolean>
        get() = observeNowPlayingEnabledUseCase().asLiveData()

    val scrobblingPoint: LiveData<Int>
        get() = observeScrobblingPointUseCase().asLiveData()

    val scrobbleShort: LiveData<Boolean>
        get() = observeScrobbleShortUseCase().asLiveData()

    private val _authLoadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val authLoadingState = _authLoadingState as LiveData<LoadingState>

    fun login(username: String, password: String) {
        _authLoadingState.value = LoadingState.Loading()
        val params = AuthUseCase.Params(username, password)
        viewModelScope.launch {
            try {
                authUseCase(params)
                _authLoadingState.value = LoadingState.Loaded()
            } catch (e: Throwable) {
                e.message?.let { messageBus.sendMessage(it) }
                _authLoadingState.value = LoadingState.Error()
            }
        }
    }

    fun logout() {
        _authLoadingState.value = LoadingState.Loaded()
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    fun enableScrobbling(isEnabled: Boolean) {
        viewModelScope.launch { setScrobblingEnabledUseCase(isEnabled) }
    }

    fun enabledNowPlaying(isEnabled: Boolean) {
        viewModelScope.launch { setNowPlayingEnabledUseCase(isEnabled) }
    }

    fun setScrobblePoint(point: Int) {
        viewModelScope.launch { setScrobblingPointUseCase(point) }
    }

    fun setScrobbleShort(isEnabled: Boolean) {
        viewModelScope.launch { setScrobbleShortUseCase(isEnabled) }
    }
}