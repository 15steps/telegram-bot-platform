package cf.moviebot.shared.domain

data class Update(
    val updateId: Long,
    val message: Message? = null,
    val editedMessage: Message? = null,
    val channelPost: Message? = null,
    val editedChannelPost: Message? = null,
    val inlineQuery: InlineQuery? = null
)
