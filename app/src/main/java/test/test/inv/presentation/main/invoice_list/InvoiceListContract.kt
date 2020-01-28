package test.test.inv.presentation.main.invoice_list

import androidx.lifecycle.LiveData
import test.test.inv.domain.data.Invoice
import test.test.inv.presentation.base.BaseViewModel

interface InvoiceListContract {

    interface ViewModel : BaseViewModel {
        val invoiceListLD: LiveData<List<Invoice>>

        fun onUpdateListClick()
        fun onGoBackClick()
    }


    interface Router {
        fun goBack()
    }

}