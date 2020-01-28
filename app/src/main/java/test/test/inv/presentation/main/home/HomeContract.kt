package test.test.inv.presentation.main.home

import test.test.inv.presentation.base.BaseViewModel

interface HomeContract {

    interface ViewModel : BaseViewModel {
        fun onInvoiceListClick()
    }

    interface Router {
        fun navigateToInvoiceListFragment()
    }

}
