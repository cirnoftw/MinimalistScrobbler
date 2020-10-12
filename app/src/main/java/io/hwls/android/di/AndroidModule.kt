package io.hwls.android.di

import io.hwls.android.feature.settings.SettingsFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

internal val androidModule = module {
    fragment { SettingsFragment() }
}