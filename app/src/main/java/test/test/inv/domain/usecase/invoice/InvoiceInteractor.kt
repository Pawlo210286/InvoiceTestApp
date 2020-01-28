package test.test.inv.domain.usecase.invoice

import androidx.lifecycle.LiveData
import test.test.inv.domain.data.Customer
import test.test.inv.domain.data.Invoice
import test.test.inv.domain.data.InvoiceState
import test.test.inv.domain.gateway.customer.ICustomerGateway
import test.test.inv.domain.gateway.invoice.IInvoiceGateway

class InvoiceInteractor(
    private val invoiceGateway: IInvoiceGateway,
    private val customerGateway: ICustomerGateway
) : InvoiceUseCase {

    override fun getAllInvoices(): LiveData<List<Invoice>> {
        return invoiceGateway.getAllInvoices()
    }

    override suspend fun saveInvoice(invoice: Invoice) {
        invoiceGateway.saveInvoice(invoice)
    }

    override suspend fun fetchInvoiceList() {
        val testInvoice = listOf(
            Invoice(
                id = 1,
                number = "I12347",
                status = "Opened",
                date = "2020-02-15",
                balance = 15000,
                customer = Customer(id = 3, name = "Bradley Garkel"),
                state = InvoiceState.PAID
            ),
            Invoice(
                id = 2,
                number = "I12345",
                status = "",
                date = "2020-01-13",
                balance = 20000,
                customer = Customer(id = 1, name = "Charlie Mayfield"),
                state = InvoiceState.LATE
            ),
            Invoice(
                id = 3,
                number = "I12346",
                status = "Viewed",
                date = "2020-02-05",
                balance = 37500,
                customer = Customer(id = 2, name = "Sam Swansom"),
                state = InvoiceState.OPEN
            )
        )

        testInvoice.forEach {
            customerGateway.saveCustomer(it.customer)
            invoiceGateway.saveInvoice(it)
        }
    }
}