package io.hwls.android.di

import io.hwls.android.common.navigation.FlowNavigationViewModel
import io.hwls.android.feature.list.ScrobblesListViewModel
import io.hwls.android.feature.settings.SettingsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
internal val viewModels = module {
    viewModel { FlowNavigationViewModel(get()) }

    viewModel { ScrobblesListViewModel() }
    viewModel {
        SettingsViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}