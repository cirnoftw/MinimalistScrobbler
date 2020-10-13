package io.hwls.android.common

@Suppress("CanSealedSubClassBeObject")
sealed class LoadingState {
    class Loading : LoadingState()
    class Loaded(val data: Any? = null) : LoadingState()
    class Error(val errorMessage: String? = null) : LoadingState()
}