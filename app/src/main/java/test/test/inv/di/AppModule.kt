package test.test.inv.di

import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import test.test.inv.App
import test.test.inv.domain.di.DomainModule

object AppModule {
    fun get(application: App) = Kodein.Module("AppModule") {
        import(androidModule(application))
        import(DomainModule.get())

        bind<Context>() with provider { application }
    }
}