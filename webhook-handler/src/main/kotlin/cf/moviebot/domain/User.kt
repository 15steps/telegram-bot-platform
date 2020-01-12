package cf.moviebot.domain

data class User(
    val id: Long,
    val isBot: Boolean,
    val firstName: String,
    val lastName: String?,
    val username: String,
    val languageCode: String?
)