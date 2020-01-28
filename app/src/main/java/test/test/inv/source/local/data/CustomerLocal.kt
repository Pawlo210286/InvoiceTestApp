package test.test.inv.source.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerLocal(
    @PrimaryKey
    val id: Long,
    val name: String
)