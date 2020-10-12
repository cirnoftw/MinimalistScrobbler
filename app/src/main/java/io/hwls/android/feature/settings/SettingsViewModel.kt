package io.hwls.android.feature.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.hwls.android.common.MessageBus
import io.hwls.domain.model.LoginState
import io.hwls.domain.usecase.AuthUseCase
import io.hwls.domain.usecase.LogoutUseCase
import io.hwls.domain.usecase.ObserveLoginStateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
class SettingsViewModel(
    private val authUseCase: AuthUseCase,
    private val observeLoginStateUseCase: ObserveLoginStateUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val messageBus: MessageBus
) : ViewModel() {
    val loginState: LiveData<LoginState?>
        get() = observeLoginStateUseCase.execute().asLiveData()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val params = AuthUseCase.Params(username, password)
            try {
                authUseCase.execute(params)
            } catch (e: Throwable) {
                e.message?.let { messageBus.sendMessage(it) }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute()
        }
    }
}