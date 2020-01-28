package test.test.inv.domain.data

data class Invoice(
    val id: Long,
    val number: String,
    val status: String,
    val date: String,
    val balance: Long,
    val customer: Customer,
    val state: InvoiceState
)