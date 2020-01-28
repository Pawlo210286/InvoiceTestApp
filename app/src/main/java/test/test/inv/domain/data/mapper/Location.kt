package test.test.inv.domain.data.mapper

import test.test.inv.domain.data.location.Location
import test.test.inv.source.local.data.LocationLocal

fun Location.toLocationLocal(): LocationLocal {
    return LocationLocal(
        id = this@toLocationLocal.id,
        lat = this@toLocationLocal.lat,
        lon = this@toLocationLocal.lon,
        date = this@toLocationLocal.data
    )
}

fun LocationLocal.toEntity(): Location {
    return Location(
        id = this@toEntity.id,
        lat = this@toEntity.lat,
        lon = this@toEntity.lon,
        data = this@toEntity.date
    )
}

fun List<LocationLocal>.toEntity(): List<Location> {
    return this.map {
        it.toEntity()
    }
}
