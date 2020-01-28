package test.test.inv.domain.usecase.location

import test.test.inv.domain.data.location.Location

interface LocationUseCase {

    suspend fun updateLocations(location: Location)

}