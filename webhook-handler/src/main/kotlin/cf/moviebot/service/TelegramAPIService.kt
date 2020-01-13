package cf.moviebot.service

import cf.moviebot.shared.domain.User
import cf.moviebot.shared.domain.failed
import cf.moviebot.shared.extension.getApiResult
import cf.moviebot.shared.http.RestTemplateRepository
import cf.moviebot.shared.logging.logger
import org.springframework.stereotype.Service

@Service
class TelegramAPIService(
    private val restRepository: RestTemplateRepository
) {
    fun getMe(token: String): User? {
        val result = restRepository
                .find(token)
                .getApiResult<User>("/getMe")
        return if (result.failed()) {
            logger().error("Error getting me. result=$result")
            null
        } else {
            result?.result
        }
    }
}