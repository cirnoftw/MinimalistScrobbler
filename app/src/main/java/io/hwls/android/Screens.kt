package io.hwls.android

import androidx.core.os.bundleOf
import io.hwls.android.feature.settings.SettingsFlowFragment
import io.hwls.android.feature.settings.SettingsFragment
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
        class Settings : SupportAppScreen() {
            override fun getFragmentParams(): FragmentParams? =
                FragmentParams(SettingsFragment::class.java, bundleOf())
        }
    }
}