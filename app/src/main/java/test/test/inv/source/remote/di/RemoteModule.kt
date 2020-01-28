package test.test.inv.source.remote.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import test.test.inv.source.remote.repository.location.ILocationRepository
import test.test.inv.source.remote.repository.location.LocationRepository

object RemoteModule {
    fun get() = Kodein.Module("RemoteModule") {
        bind<ILocationRepository>() with provider { LocationRepository() }
    }
}