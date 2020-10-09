package io.hwls.android.di

import io.hwls.android.feature.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModels = module {
    viewModel { OverviewViewModel() }
}