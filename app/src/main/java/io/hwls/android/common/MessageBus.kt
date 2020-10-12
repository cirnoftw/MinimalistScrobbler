package io.hwls.android.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

data class SystemMessage(
    val message: String,
    val actionMessage: String? = null,
    val actionCallback: (() -> Unit)? = null
)

@FlowPreview
@ExperimentalCoroutinesApi
class MessageBus(private val context: Context) {
    private val _messageBus: MutableLiveData<SystemMessage> = MutableLiveData()
    val messageBus: LiveData<SystemMessage> = _messageBus

    fun sendMessage(message: String) {
        _messageBus.value = SystemMessage(message)
    }

    fun sendMessage(@StringRes messageRes: Int) {
        _messageBus.value = SystemMessage(context.getString(messageRes))
    }

    fun sendActionMessage(
        message: String,
        @StringRes actionMessageRes: Int,
        callback: () -> Unit
    ) {
        _messageBus.value = (SystemMessage(message, context.getString(actionMessageRes), callback))
    }

    fun sendActionMessage(
        @StringRes messageRes: Int,
        @StringRes actionMessageRes: Int,
        callback: () -> Unit
    ) {
        _messageBus.value = (SystemMessage(
            context.getString(messageRes),
            context.getString(actionMessageRes),
            callback
        ))
    }
}