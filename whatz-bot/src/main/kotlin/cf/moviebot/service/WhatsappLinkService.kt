package cf.moviebot.service

import cf.moviebot.shared.domain.Update
import cf.moviebot.shared.logging.logger
import org.springframework.stereotype.Service

@Service
class WhatsappLinkService(
    private val telegramAPIService: TelegramAPIService
) {
    companion object {
        const val MARKDOWN_MODE = "MarkdownV2"
    }
    fun sendChatLink(token: String, update: Update) {
        val chatId = requireNotNull(update.message?.chat?.id) { "username is required" }
        val phone = requireNotNull(update.message?.text) { "text is required" }
        val params = mapOf(
            "chat_id" to chatId.toString(),
            "text" to buildMessageTemplate(phone),
            "parseMode" to MARKDOWN_MODE
        )
        val result = telegramAPIService.sendMessage(token, params)
        logger().info("Message sent to Telegram API. message=$result")
    }

    private fun buildChatLink(phone: String): String {
        TODO()
    }

    private fun buildMessageTemplate(phone: String): String =
        "Select the following link to open Whatsapp chat: https://api.whatsapp.com/send?phone=$phone"
}