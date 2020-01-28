package test.test.inv.domain.usecase.location

import test.test.inv.domain.data.location.Location
import test.test.inv.domain.gateway.location.ILocationGateway

internal class LocationInteractor(
    private val locationGateway: ILocationGateway
) : LocationUseCase {

    override suspend fun updateLocations(location: Location) {
        locationGateway.getAllLocations().toMutableList().let { lastLocations ->

            if (lastLocations.size >= 3) {
                val invalidLocationsCount = (lastLocations.size - (lastLocations.size - 2)) - 1
                val invalidLocations = lastLocations.subList(0, invalidLocationsCount)
                locationGateway.removeLocation(invalidLocations)
                lastLocations.removeAll(invalidLocations)
            }

            lastLocations.add(location)

            locationGateway.saveLocations(lastLocations)
            locationGateway.sendLocationsToServer(lastLocations)
        }
    }

}