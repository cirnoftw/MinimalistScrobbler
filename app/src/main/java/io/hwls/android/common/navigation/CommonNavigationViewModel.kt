package io.hwls.android.common.navigation

import androidx.lifecycle.ViewModel
import io.hwls.android.common.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class CommonNavigationViewModel(private val appRouter: Router) : ViewModel() {
    private val flowCicerone: Cicerone<FlowRouter> by lazy {
        Cicerone.create(FlowRouter(appRouter))
    }

    val router: FlowRouter
        get() = flowCicerone.router

    val navigatorHolder: NavigatorHolder
        get() = flowCicerone.navigatorHolder
}
