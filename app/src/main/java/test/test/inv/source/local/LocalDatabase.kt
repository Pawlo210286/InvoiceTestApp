package test.test.inv.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import test.test.inv.source.local.data.CustomerLocal
import test.test.inv.source.local.data.InvoiceLocal
import test.test.inv.source.local.data.LocationLocal
import test.test.inv.source.local.repository.customer.CustomerDao
import test.test.inv.source.local.repository.invoice.InvoiceDao
import test.test.inv.source.local.repository.location.LocationDao

@Database(
    entities = [
        CustomerLocal::class,
        InvoiceLocal::class,
        LocationLocal::class
    ],
    version = 1
)
internal abstract class LocalDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun locationDao(): LocationDao

    companion object {
        const val DATABASE_NAME = "TestDB"
    }
}