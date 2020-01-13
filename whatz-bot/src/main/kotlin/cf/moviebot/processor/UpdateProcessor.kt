package cf.moviebot.processor

import cf.moviebot.service.WhatsappLinkService
import cf.moviebot.shared.domain.Update
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component

@Component
class UpdateProcessor(
    private val whatsappLinkService: WhatsappLinkService
) {
    companion object {
        const val WHATZ_BOT_QUEUE = "whatz_bot.webhook.mo"
    }

    @RabbitListener(queues = [WHATZ_BOT_QUEUE])
    fun listen(update: Update, @Headers headers: Map<String, Any>) {
        val token = requireNotNull(headers["token"]) { "token is required" } as String
        whatsappLinkService.sendChatLink(token, update)
    }
}