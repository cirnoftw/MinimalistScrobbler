package io.hwls.android.di

import androidx.preference.PreferenceManager
import com.tfcporciuncula.flow.FlowSharedPreferences
import io.hwls.android.common.MessageBus
import io.hwls.data.prefs.PrefsWrapper
import io.hwls.domain.common.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    single<DispatcherProvider> {
        object : DispatcherProvider {
            override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
            override val default: CoroutineDispatcher = Dispatchers.Default
            override val io: CoroutineDispatcher = Dispatchers.IO
            override val main: CoroutineDispatcher = Dispatchers.Main
        }
    }
}