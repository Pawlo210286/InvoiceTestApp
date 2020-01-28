package test.test.inv.source.local.repository.location

import test.test.inv.source.local.data.LocationLocal

interface ILocationRepository {
    suspend fun saveLocations(locations: List<LocationLocal>)
    suspend fun removeLocations(locations: List<LocationLocal>)
    suspend fun getLocations(): List<LocationLocal>
}