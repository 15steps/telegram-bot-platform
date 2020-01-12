package cf.moviebot.shared.domain


data class Message(
    val messageId: Long,
    val text: String?,
    val from: User?,
    val date: Long
)