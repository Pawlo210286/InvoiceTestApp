package test.test.inv.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import test.test.inv.R
import test.test.inv.presentation.main.home.HomeContract
import test.test.inv.presentation.main.home.HomeFragment
import test.test.inv.presentation.main.invoice_list.InvoiceListContract
import test.test.inv.presentation.main.invoice_list.InvoiceListFragment

class MainRouter(
    private val manager: FragmentManager,
    private val containerId: Int
) : HomeContract.Router, InvoiceListContract.Router {

    fun showHomeFragment() {
        HomeFragment.newInstance().replace()
    }

    override fun navigateToInvoiceListFragment() {
        InvoiceListFragment.newInstance().replaceWithBackStack()
    }

    override fun goBack() {
        manager.popBackStack()
    }

    private fun Fragment.replace() {
        manager.beginTransaction()
            .replace(containerId, this, this::class.java.simpleName)
            .commit()
    }

    private fun Fragment.replaceWithBackStack() {
        manager.beginTransaction()
            .setCustomAnimations(
                R.anim.anim_fade_in,
                R.anim.anim_fade_out,
                R.anim.anim_fade_in,
                R.anim.anim_fade_out
            )
            .replace(containerId, this, this::class.java.simpleName)
            .addToBackStack(this::class.java.simpleName)
            .commit()
    }

}
