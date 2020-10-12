package io.hwls.android.feature.settings

import io.hwls.android.Screens
import io.hwls.android.common.ui.FlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SettingsFlowFragment : FlowFragment() {
    override val launchScreen: SupportAppScreen? = Screens.Screen.Settings()
}