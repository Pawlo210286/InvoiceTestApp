package test.test.inv.source.local.repository.location

import test.test.inv.source.local.data.LocationLocal

class LocationRepository(
    private val dao: LocationDao
) : ILocationRepository {

    override suspend fun saveLocations(locations: List<LocationLocal>) {
        dao.saveLocations(locations)
    }

    override suspend fun removeLocations(locations: List<LocationLocal>) {
        dao.deleteLocations(locations)
    }

    override suspend fun getLocations(): List<LocationLocal> {
        return dao.getAllLocations() ?: emptyList()
    }
}
