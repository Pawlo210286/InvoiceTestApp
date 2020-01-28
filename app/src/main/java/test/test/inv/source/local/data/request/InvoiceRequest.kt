package test.test.inv.source.local.data.request

import androidx.room.Embedded
import androidx.room.Relation
import test.test.inv.source.local.data.CustomerLocal
import test.test.inv.source.local.data.InvoiceLocal

data class InvoiceRequest(
    @Embedded
    val invoice: InvoiceLocal,
    @Relation(parentColumn = "customerId", entityColumn = "id")
    val customer: CustomerLocal?
)