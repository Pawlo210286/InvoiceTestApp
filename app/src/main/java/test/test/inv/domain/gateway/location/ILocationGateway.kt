package test.test.inv.domain.gateway.location

import test.test.inv.domain.data.location.Location

interface ILocationGateway {

    suspend fun getAllLocations(): List<Location>
    suspend fun removeLocation(location: List<Location>)
    suspend fun saveLocations(locations: List<Location>)

    suspend fun sendLocationsToServer(locations: List<Location>): Boolean

}