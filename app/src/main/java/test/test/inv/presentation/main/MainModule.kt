package test.test.inv.presentation.main

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import test.test.inv.R

object MainModule {
    fun get(activity: MainActivity) = Kodein.Module(MainModule::class.java.simpleName) {
        bind<MainRouter>() with provider {
            MainRouter(
                manager = activity.supportFragmentManager,
                containerId = R.id.fragmentContainer
            )
        }
    }
}