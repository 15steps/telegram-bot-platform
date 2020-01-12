package cf.moviebot.shared.domain

data class InlineQuery(
    val id: String,
    val user: User,
    val query: String,
    val offset: String,
    val location: Location?
)
