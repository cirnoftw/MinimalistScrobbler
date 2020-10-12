package io.hwls.android.di

import androidx.preference.PreferenceManager
import com.tfcporciuncula.flow.FlowSharedPreferences
import io.hwls.android.common.MessageBus
import io.hwls.data.prefs.PrefsWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
internal val appModule = module {
    single { MessageBus(get()) }

    factory { PreferenceManager.getDefaultSharedPreferences(get()) }

    single { FlowSharedPreferences(get()) }

    single { PrefsWrapper(get()) }
}