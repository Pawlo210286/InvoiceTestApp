package test.test.inv.source.remote.data

data class RemoteResponse<T>(
    val isSuccessful: Boolean,
    val code: Int,
    val message: String,
    val successModel: T?
)