package test.test.inv.presentation.main.home

import test.test.inv.presentation.base.BaseFactory

class HomeViewModelFactory(
    private val router: HomeContract.Router
) : BaseFactory<HomeViewModel>() {

    override fun createViewModel(): HomeViewModel {
        return HomeViewModel(router)
    }

}
