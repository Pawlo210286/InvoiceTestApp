package test.test.inv.domain.usecase.invoice

import androidx.lifecycle.LiveData
import test.test.inv.domain.data.Invoice

interface InvoiceUseCase {
    suspend fun saveInvoice(invoice: Invoice)
    suspend fun fetchInvoiceList()
    fun getAllInvoices(): LiveData<List<Invoice>>
}