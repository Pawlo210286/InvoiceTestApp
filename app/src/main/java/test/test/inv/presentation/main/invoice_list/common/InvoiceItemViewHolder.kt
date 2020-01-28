package test.test.inv.presentation.main.invoice_list.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_invoice.view.*
import test.test.inv.R
import test.test.inv.domain.data.Invoice
import test.test.inv.presentation.util.fromRequestToDisplayDate

class InvoiceItemViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {


    fun bind(invoice: Invoice) {

        itemView.apply {

            tv_user_name.text = invoice.customer.name
            tv_number.text = invoice.number
            tv_date.text = invoice.date.fromRequestToDisplayDate()
            tv_sent_status.text = invoice.status
            tv_balance.text =
                resources.getString(R.string.balance, String.format("%.2f", invoice.balance / 100f))

            tv_invoice_state.text = resources.getString(invoice.state.title)
            mcv_invoice_state.setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    invoice.state.color
                )
            )
        }

    }


    companion object {
        fun create(parent: ViewGroup): InvoiceItemViewHolder {
            return InvoiceItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_invoice, parent, false)
            )
        }
    }

}