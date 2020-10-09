package io.hwls.android

import android.app.Application
import io.hwls.android.di.appModules
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication

@ExperimentalSerializationApi
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        koinApplication {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(appModules)
        }
    }
}