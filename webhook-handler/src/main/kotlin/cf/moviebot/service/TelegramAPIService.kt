package cf.moviebot.service

import cf.moviebot.extension.getApiResult
import cf.moviebot.extension.toBotUri
import cf.moviebot.logging.logger
import cf.moviebot.shared.domain.User
import cf.moviebot.shared.domain.failed
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

typealias BotId = String

@Service
class TelegramAPIService(
    private val botAPIRestTemplate: RestTemplate
) {
    fun getMe(botId: BotId): User? {
        val result = botAPIRestTemplate
                .getApiResult<User>(botId.toBotUri("/getMe"))
        return if (result.failed()) {
            logger().error("Error getting me. result=$result")
            null
        } else {
            result?.result
        }
    }
}