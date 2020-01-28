package test.test.inv.domain.gateway.invoice

import androidx.lifecycle.LiveData
import test.test.inv.domain.data.Invoice

interface IInvoiceGateway {
    suspend fun saveInvoice(invoice: Invoice)
    fun getAllInvoices(): LiveData<List<Invoice>>
}