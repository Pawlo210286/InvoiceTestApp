package test.test.inv.presentation.main.invoice_list.common

import androidx.recyclerview.widget.DiffUtil
import test.test.inv.domain.data.Invoice

class InvoiceItemDiffUtil(
    private val oldList: List<Invoice>,
    private val newList: List<Invoice>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}