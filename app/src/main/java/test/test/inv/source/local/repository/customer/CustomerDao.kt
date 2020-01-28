package test.test.inv.source.local.repository.customer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import test.test.inv.source.local.data.CustomerLocal

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCustomer(customer: CustomerLocal)

}