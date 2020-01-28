package test.test.inv.source.local.repository.location

import androidx.room.*
import test.test.inv.source.local.data.LocationLocal

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocations(location: List<LocationLocal>)

    @Delete
    suspend fun deleteLocations(locations: List<LocationLocal>)

    @Query("SELECT * FROM LocationLocal ORDER BY date")
    suspend fun getAllLocations(): List<LocationLocal>?

}