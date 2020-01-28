package test.test.inv.source.local.repository.invoice

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import test.test.inv.source.local.data.InvoiceLocal
import test.test.inv.source.local.data.request.InvoiceRequest

@Dao
interface InvoiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInvoice(invoice: InvoiceLocal)

    @Query("SELECT * FROM InvoiceLocal ORDER BY date")
    fun getAllData(): LiveData<List<InvoiceRequest>>

}