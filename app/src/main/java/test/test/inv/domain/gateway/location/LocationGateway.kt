package test.test.inv.domain.gateway.location

import test.test.inv.domain.data.location.Location
import test.test.inv.domain.data.mapper.toEntity
import test.test.inv.domain.data.mapper.toLocationLocal
import test.test.inv.source.local.repository.location.ILocationRepository as LocationRepositoryLocal
import test.test.inv.source.remote.repository.location.ILocationRepository as LocationRepositoryRemote

internal class LocationGateway(
    private val remote: LocationRepositoryRemote,
    private val local: LocationRepositoryLocal
) : ILocationGateway {
    override suspend fun getAllLocations(): List<Location> {
        return local.getLocations().toEntity()
    }

    override suspend fun removeLocation(location: List<Location>) {
        local.removeLocations(location.map { it.toLocationLocal() })
    }

    override suspend fun saveLocations(locations: List<Location>) {
        local.saveLocations(locations.map { it.toLocationLocal() })
    }

    override suspend fun sendLocationsToServer(locations: List<Location>): Boolean {
        //todo: send location
        return remote.sendLocationsToServer(header = "", request = "").isSuccessful
    }
}