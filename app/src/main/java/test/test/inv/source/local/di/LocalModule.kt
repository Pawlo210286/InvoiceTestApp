package test.test.inv.source.local.di

import androidx.room.Room
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import test.test.inv.source.local.LocalDatabase
import test.test.inv.source.local.repository.customer.CustomerDao
import test.test.inv.source.local.repository.customer.CustomerRepository
import test.test.inv.source.local.repository.customer.ICustomerRepository
import test.test.inv.source.local.repository.invoice.IInvoiceRepository
import test.test.inv.source.local.repository.invoice.InvoiceDao
import test.test.inv.source.local.repository.invoice.InvoiceRepository
import test.test.inv.source.local.repository.location.ILocationRepository
import test.test.inv.source.local.repository.location.LocationDao
import test.test.inv.source.local.repository.location.LocationRepository

object LocalModule {

    fun get() = Kodein.Module("LocalModule") {
        bind<LocalDatabase>() with singleton {
            Room.databaseBuilder<LocalDatabase>(
                instance(),
                LocalDatabase::class.java,
                LocalDatabase.DATABASE_NAME
            )
                .build()
        }

        applyDaoModule()
        applyRepositoryModule()
    }

    private fun Kodein.Builder.applyDaoModule() {
        bind<CustomerDao>() with provider { instance<LocalDatabase>().customerDao() }
        bind<InvoiceDao>() with provider { instance<LocalDatabase>().invoiceDao() }
        bind<LocationDao>() with provider { instance<LocalDatabase>().locationDao() }
    }

    private fun Kodein.Builder.applyRepositoryModule() {
        bind<ICustomerRepository>() with provider { CustomerRepository(instance()) }
        bind<IInvoiceRepository>() with provider { InvoiceRepository(instance()) }
        bind<ILocationRepository>() with provider { LocationRepository(instance()) }
    }
}