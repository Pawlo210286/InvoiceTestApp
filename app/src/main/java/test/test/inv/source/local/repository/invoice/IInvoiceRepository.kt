package test.test.inv.source.local.repository.invoice

import androidx.lifecycle.LiveData
import test.test.inv.source.local.data.InvoiceLocal
import test.test.inv.source.local.data.request.InvoiceRequest

interface IInvoiceRepository {
    suspend fun saveInvoice(invoice: InvoiceLocal)
    fun getAllInvoices(): LiveData<List<InvoiceRequest>>
}