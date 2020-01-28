package test.test.inv.presentation.main.invoice_list.common

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import test.test.inv.domain.data.Invoice

class InvoiceListAdapter : RecyclerView.Adapter<InvoiceItemViewHolder>() {

    private val items = mutableListOf<Invoice>()


    fun updateItems(items: List<Invoice>) {
        DiffUtil.calculateDiff(
            InvoiceItemDiffUtil(
                oldList = this.items,
                newList = items
            )
        ).also {
            this.items.clear()
            this.items.addAll(items)
            it.dispatchUpdatesTo(this)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceItemViewHolder {
        return InvoiceItemViewHolder.create(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InvoiceItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

}