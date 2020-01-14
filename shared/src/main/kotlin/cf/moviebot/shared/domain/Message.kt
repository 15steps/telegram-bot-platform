package cf.moviebot.shared.domain

data class Message(
    val messageId: Long,
    val text: String? = null,
    val from: User? = null,
    val date: Long,
    val chat: Chat
)