package test.test.inv.presentation.main.home

import test.test.inv.presentation.base.BaseViewModelImpl

class HomeViewModel(
    private val router: HomeContract.Router
) : BaseViewModelImpl(), HomeContract.ViewModel {

    override fun onInvoiceListClick() {
        router.navigateToInvoiceListFragment()
    }

}
