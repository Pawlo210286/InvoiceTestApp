package test.test.inv.source.remote.repository.location

import test.test.inv.domain.data.location.LocationsResponse
import test.test.inv.source.remote.data.RemoteResponse

interface ILocationRepository {
    suspend fun sendLocationsToServer(
        header: String,
        request: String
    ): RemoteResponse<LocationsResponse>
}