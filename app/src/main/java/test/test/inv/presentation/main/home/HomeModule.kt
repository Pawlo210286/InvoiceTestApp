package test.test.inv.presentation.main.home

import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object HomeModule {
    fun get(fragment: HomeFragment) = Kodein.Module(HomeModule::class.java.simpleName) {
        bind<ViewModelProvider.Factory>("HomeModule") with singleton {
            HomeViewModelFactory(instance())
        }
        bind<HomeContract.ViewModel>() with provider {
            fragment.vm<HomeViewModel>(instance("HomeModule"))
        }
    }
}