package io.hwls.android.di

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

val navigation = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.navigatorHolder }

//    val flowRouter: ScopeDSL.() -> Unit = {
//        scoped<Cicerone<FlowRouter>> { Cicerone.create(FlowRouter(get())) }
//        factory { get<Cicerone<FlowRouter>>().router }
//        factory { get<Cicerone<FlowRouter>>().navigatorHolder }
//    }
//
//    scope(named("SettingsFlow"), flowRouter)
}