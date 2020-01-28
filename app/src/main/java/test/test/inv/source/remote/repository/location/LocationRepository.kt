package test.test.inv.source.remote.repository.location

import kotlinx.coroutines.delay
import test.test.inv.domain.data.location.LocationsResponse
import test.test.inv.source.remote.data.RemoteResponse

class LocationRepository : ILocationRepository {

    override suspend fun sendLocationsToServer(
        header: String,
        request: String
    ): RemoteResponse<LocationsResponse> {
        delay(1000)
        return RemoteResponse(
            true,
            200,
            "ok",
            LocationsResponse(
                "success",
                null
            )
        )
    }
}