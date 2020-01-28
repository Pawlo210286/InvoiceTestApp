package test.test.inv.presentation.main.invoice_list

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.test.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_invoice_list.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import test.test.inv.R
import test.test.inv.databinding.FragmentInvoiceListBinding
import test.test.inv.presentation.main.invoice_list.common.InvoiceListAdapter

class InvoiceListFragment : BaseFragment<FragmentInvoiceListBinding>() {

    override val kodeinModule: Kodein.Module = InvoiceListModule.get(this)
    override val viewModel: InvoiceListContract.ViewModel by instance()


    private val invoiceAdapter by lazy {
        InvoiceListAdapter()
    }

    private val dividerDecorator by lazy {
        DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
                setDrawable(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        binding.rvContent.apply {
            adapter = invoiceAdapter
            addItemDecoration(dividerDecorator)
        }

        setupToolbar(activity as? AppCompatActivity)

        viewModel.invoiceListLD.observe(viewLifecycleOwner, Observer {
            invoiceAdapter.updateItems(it)
            updateEmptyViewState(it.size)
        })
    }

    private fun setupToolbar(activity: AppCompatActivity?) {
        activity?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun updateEmptyViewState(count: Int) {
        if (count > 0) {
            empty_view.visibility = View.GONE
            rv_content.visibility = View.VISIBLE
        } else {
            empty_view.visibility = View.VISIBLE
            rv_content.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            viewModel.onGoBackClick()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        fun newInstance() = InvoiceListFragment()
    }

}