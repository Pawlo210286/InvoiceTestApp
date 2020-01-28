package test.test.inv.source.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InvoiceLocal(
    @PrimaryKey
    val id: Long,
    val number: String,
    val status: String,
    val date: String,
    val balance: Long,
    val customerId: Long,
    val state: String
)