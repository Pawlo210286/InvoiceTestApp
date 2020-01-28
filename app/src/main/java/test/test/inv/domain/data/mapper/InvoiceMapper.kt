package test.test.inv.domain.data.mapper

import test.test.inv.domain.data.Customer
import test.test.inv.domain.data.Invoice
import test.test.inv.domain.data.fromString
import test.test.inv.source.local.data.InvoiceLocal
import test.test.inv.source.local.data.request.InvoiceRequest

fun Invoice.toLocal(): InvoiceLocal {
    return InvoiceLocal(
        id = id,
        status = status,
        date = date,
        balance = balance,
        customerId = customer.id,
        number = number,
        state = state.toString()
    )
}

fun InvoiceRequest.toEntity(): Invoice {
    return Invoice(
        id = invoice.id,
        number = invoice.number,
        balance = invoice.balance,
        date = invoice.date,
        status = invoice.status,
        customer = customer?.toEntity() ?: Customer.empty(),
        state = fromString(invoice.state)
    )
}