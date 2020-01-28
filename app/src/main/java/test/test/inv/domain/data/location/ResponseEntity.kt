package test.test.inv.domain.data.location

data class ResponseEntity<T>(
    val isSuccessful: Boolean,
    val code: Int,
    val message: String,
    val successModel: T?
)