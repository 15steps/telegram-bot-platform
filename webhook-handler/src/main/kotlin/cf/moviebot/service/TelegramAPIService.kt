package cf.moviebot.service

import cf.moviebot.domain.Me
import cf.moviebot.domain.failed
import cf.moviebot.extension.getForEntity
import cf.moviebot.extension.toBotUri
import cf.moviebot.logging.logger
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

typealias BotId = String

@Service
class TelegramAPIService(
    private val botAPIRestTemplate: RestTemplate
) {
    fun getMe(botId: BotId): Me? {
        val result = botAPIRestTemplate
                .getForEntity<Me>(botId.toBotUri("/getMe"))
        return if (result.failed()) {
            logger().error("Error getting me. result=$result")
            null
        } else {
            result?.result
        }
    }
}