package cf.moviebot.shared.domain

// TODO reply_markup
// TODO add missing send* fields
data class SendMessage(
    val chatId: String,
    // sendMessage
    val text: String? = null,
    // sendPhoto
    val photo: String? = null,
    // sendAudio
    val audio: String? = null,
    // sendDocument
    val document: String? = null,
    val thumb: String? = null,
    val caption: String? = null,
    val parseMode: String? = null,
    val disableWebPagePreview: Boolean? = null,
    val disableNotification: Boolean? = null,
    val replyToMessageId: Int? = null
)