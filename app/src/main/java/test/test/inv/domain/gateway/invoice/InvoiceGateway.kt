package test.test.inv.domain.gateway.invoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import test.test.inv.domain.data.Invoice
import test.test.inv.domain.data.mapper.toEntity
import test.test.inv.domain.data.mapper.toLocal
import test.test.inv.source.local.repository.invoice.IInvoiceRepository

class InvoiceGateway(
    private val local: IInvoiceRepository
) : IInvoiceGateway {

    override fun getAllInvoices(): LiveData<List<Invoice>> {
        return local.getAllInvoices().map {
            it.map {
                it.toEntity()
            }
        }
    }

    override suspend fun saveInvoice(invoice: Invoice) {
        local.saveInvoice(invoice.toLocal())
    }
}