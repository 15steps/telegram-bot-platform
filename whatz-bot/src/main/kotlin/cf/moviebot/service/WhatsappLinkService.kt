package cf.moviebot.service

import cf.moviebot.shared.domain.SendMessage
import cf.moviebot.shared.domain.Update
import org.springframework.stereotype.Service

@Service
class WhatsappLinkService(
    private val telegramAPIService: TelegramAPIService
) {
    companion object {
        const val MARKDOWN_MODE = "MarkdownV2"
    }
    fun sendChatLink(token: String, update: Update) {
        val username = requireNotNull(update.message?.from?.username) { "username is required" }
        val phone = requireNotNull(update.message?.text) { "text is required" }
        val sendMessage = SendMessage(
            chatId = username,
            text = buildMessageTemplate(phone),
            parseMode = MARKDOWN_MODE
        )
        telegramAPIService.sendMessage(token, sendMessage)
    }

    private fun buildChatLink(phone: String): String {
        TODO()
    }

    private fun buildMessageTemplate(phone: String): String =
        """
        _Select the following link to open Whatsapp chat:_
        [https://api.whatsapp.com/send?phone=$phone](✆$phone)        
        """.trimIndent()
}