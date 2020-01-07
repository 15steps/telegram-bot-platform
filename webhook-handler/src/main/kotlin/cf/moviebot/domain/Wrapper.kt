package cf.moviebot.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiResult<T>(
    val ok: Boolean? = false,
    val errorCode: Int? = 0,
    val description: String? = "",
    val result: T? = null
)

fun <T> ApiResult<T>?.failed(): Boolean = this?.ok?.not() == true

data class Me(
    @JsonProperty("id") val id: String,
    @JsonProperty("is_bot") val isBot: Boolean,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("username") val userName: String
)