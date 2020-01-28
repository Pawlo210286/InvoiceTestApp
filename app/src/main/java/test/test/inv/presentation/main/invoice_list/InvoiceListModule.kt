package test.test.inv.presentation.main.invoice_list

import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object InvoiceListModule {
    fun get(fragment: InvoiceListFragment) =
        Kodein.Module(InvoiceListModule::class.java.simpleName) {
            bind<ViewModelProvider.Factory>("InvoiceList") with singleton {
                InvoiceListViewModelFactory(instance(), instance())
            }
            bind<InvoiceListContract.ViewModel>() with provider {
                fragment.vm<InvoiceListViewModel>(instance("InvoiceList"))
            }
        }
}