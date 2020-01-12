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
    val id: String,
    val isBot: Boolean,
    val firstName: String,
    @JsonProperty("username") val userName: String
)

data class Update(
    val updateId: Long,
    val message: Message,
    val editedMessage: String
)

data class Message(
    @JsonProperty("text") val text: String
)