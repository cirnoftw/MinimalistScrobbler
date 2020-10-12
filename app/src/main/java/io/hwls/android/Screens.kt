package io.hwls.android

import androidx.core.os.bundleOf
import io.hwls.android.common.ui.KFragmentParams
import io.hwls.android.feature.settings.SettingsFlowFragment
import io.hwls.android.feature.settings.SettingsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.terrakok.cicerone.android.support.FragmentParams
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object Flow {
        class Settings : SupportAppScreen() {
            override fun getFragmentParams(): FragmentParams? =
                FragmentParams(SettingsFlowFragment::class.java, bundleOf())
        }
    }

    object Screen {
        @ExperimentalCoroutinesApi
        @FlowPreview
        class Settings : SupportAppScreen() {
            override fun getFragmentParams(): FragmentParams? =
                KFragmentParams(SettingsFragment::class).value
        }
    }
}