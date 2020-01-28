package test.test.inv.domain.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import test.test.inv.domain.gateway.customer.CustomerGateway
import test.test.inv.domain.gateway.customer.ICustomerGateway
import test.test.inv.domain.gateway.invoice.IInvoiceGateway
import test.test.inv.domain.gateway.invoice.InvoiceGateway
import test.test.inv.domain.gateway.location.ILocationGateway
import test.test.inv.domain.gateway.location.LocationGateway
import test.test.inv.domain.usecase.customer.CustomerInteractor
import test.test.inv.domain.usecase.customer.CustomerUseCase
import test.test.inv.domain.usecase.invoice.InvoiceInteractor
import test.test.inv.domain.usecase.invoice.InvoiceUseCase
import test.test.inv.domain.usecase.location.LocationInteractor
import test.test.inv.domain.usecase.location.LocationUseCase
import test.test.inv.source.local.di.LocalModule
import test.test.inv.source.remote.di.RemoteModule

object DomainModule {
    fun get() = Kodein.Module("DomainModule") {
        import(LocalModule.get())
        import(RemoteModule.get())

        applyUseCaseModule()
        applyGatewayModule()
    }

    private fun Kodein.Builder.applyUseCaseModule() {
        bind<InvoiceUseCase>() with provider {
            InvoiceInteractor(
                invoiceGateway = instance(),
                customerGateway = instance()
            )
        }
        bind<CustomerUseCase>() with provider {
            CustomerInteractor(
                customerGateway = instance()
            )
        }
        bind<LocationUseCase>() with provider {
            LocationInteractor(
                locationGateway = instance()
            )
        }
    }

    private fun Kodein.Builder.applyGatewayModule() {
        bind<IInvoiceGateway>() with provider {
            InvoiceGateway(
                local = instance()
            )
        }
        bind<ICustomerGateway>() with provider {
            CustomerGateway(
                local = instance()
            )
        }
        bind<ILocationGateway>() with provider {
            LocationGateway(
                local = instance(),
                remote = instance()
            )
        }
    }
}