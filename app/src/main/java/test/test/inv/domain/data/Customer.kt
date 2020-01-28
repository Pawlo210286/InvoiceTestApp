package test.test.inv.domain.data

data class Customer(
    val id: Long,
    val name: String
) {
    companion object {
        fun empty() = Customer(
            id = -1L,
            name = ""
        )
    }
}