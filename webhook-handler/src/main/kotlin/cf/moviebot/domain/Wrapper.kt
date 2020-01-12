package cf.moviebot.domain

data class ApiResult<T>(
    val ok: Boolean? = false,
    val errorCode: Int? = 0,
    val description: String? = "",
    val result: T? = null
)

fun <T> ApiResult<T>?.failed(): Boolean = this?.ok?.not() == true