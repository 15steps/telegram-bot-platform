package cf.moviebot.service

import cf.moviebot.shared.domain.Message
import cf.moviebot.shared.extension.postApiResult
import cf.moviebot.shared.http.RestTemplateRepository
import cf.moviebot.shared.logging.logger
import org.springframework.stereotype.Service

@Service
class TelegramAPIService(
        private val restRepository: RestTemplateRepository
) {
    fun sendMessage(token: String, params: Map<String, String> = mapOf()): Message? {
        logger().info("/sendMessage to telegram API. token=${token.anonymize()}, params=$params")
        return restRepository
                .find(token)
                .postApiResult<Message>("/sendMessage", params)
                ?.result
    }

    private fun String.anonymize() = "...${this.takeLast(5)}"
}