package test.test.inv.domain.data.location

data class Location(
    val id: Long = -1,
    val lat: String = "",
    val lon: String = "",
    val data: String = ""
) {
    override fun toString(): String {
        return "lat=$lat&lon=$lon&date=$data"
    }
}