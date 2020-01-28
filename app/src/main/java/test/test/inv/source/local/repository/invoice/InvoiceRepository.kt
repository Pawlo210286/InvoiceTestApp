package test.test.inv.source.local.repository.invoice

import androidx.lifecycle.LiveData
import test.test.inv.source.local.data.InvoiceLocal
import test.test.inv.source.local.data.request.InvoiceRequest

class InvoiceRepository(
    private val dao: InvoiceDao
) : IInvoiceRepository {

    override fun getAllInvoices(): LiveData<List<InvoiceRequest>> {
        return dao.getAllData()
    }

    override suspend fun saveInvoice(invoice: InvoiceLocal) {
        dao.saveInvoice(invoice)
    }
}