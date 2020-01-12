package cf.moviebot.shared.domain

data class Update(
    val updateId: Long,
    val message: Message?,
    val editedMessage: Message?,
    val channelPost: Message?,
    val editedChannelPost: Message?,
    val inlineQuery: InlineQuery?
)
