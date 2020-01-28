package test.test.inv.presentation.main.invoice_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.test.inv.domain.data.Invoice
import test.test.inv.domain.usecase.invoice.InvoiceUseCase
import test.test.inv.presentation.base.BaseViewModelImpl

class InvoiceListViewModel(
    private val router: InvoiceListContract.Router,
    private val invoiceUseCase: InvoiceUseCase
) : BaseViewModelImpl(), InvoiceListContract.ViewModel {

    override val invoiceListLD: LiveData<List<Invoice>> = invoiceUseCase.getAllInvoices()

    override fun onUpdateListClick() {
        viewModelScope.launch {
            invoiceUseCase.fetchInvoiceList()
        }
    }

    override fun onGoBackClick() {
        router.goBack()
    }
}