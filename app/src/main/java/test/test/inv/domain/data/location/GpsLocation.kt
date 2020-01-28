package test.test.inv.domain.data.location

class GpsLocation(
    var lat: Double? = null,
    var lon: Double? = null
) {

    fun isNotEmpty(): Boolean {
        return lat != null && lon != null
    }

    fun getLatNotNull(): String {
        return lat.toString()
    }

    fun getLonNotNull(): String {
        return lon.toString()
    }
}