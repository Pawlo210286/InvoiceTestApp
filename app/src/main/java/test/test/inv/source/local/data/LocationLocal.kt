package test.test.inv.source.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val lat: String = "",
    val lon: String = "",
    val date: String = ""
)