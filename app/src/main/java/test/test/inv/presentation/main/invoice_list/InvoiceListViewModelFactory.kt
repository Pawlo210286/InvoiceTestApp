package test.test.inv.presentation.main.invoice_list

import test.test.inv.domain.usecase.invoice.InvoiceUseCase
import test.test.inv.presentation.base.BaseFactory

class InvoiceListViewModelFactory(
    private val router: InvoiceListContract.Router,
    private val invoiceUseCase: InvoiceUseCase
) : BaseFactory<InvoiceListViewModel>() {

    override fun createViewModel(): InvoiceListViewModel {
        return InvoiceListViewModel(router, invoiceUseCase)
    }

}