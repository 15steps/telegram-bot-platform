package cf.moviebot.shared.http

import cf.moviebot.shared.logging.logger
import org.apache.commons.lang3.time.StopWatch
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RootUriTemplateHandler
import org.springframework.web.client.RestTemplate

class RestTemplateRepository(
    private val baseEndpoint: String
) {
    private val repository: MutableMap<String, RestTemplate> = mutableMapOf()

    fun find(token: String): RestTemplate {
        return when(val template = repository[token]) {
            null -> registerTemplate(token)
            else -> {
                logger().info("Template cached, skipping registration. token=${token.anonymize()}")
                template
            }
        }
    }

    private fun registerTemplate(token: String): RestTemplate {
        logger().info("Registering new RestTemplate. token=${token.anonymize()}")
        val watch = StopWatch.createStarted()
        val template = buildRestTemplate(token)
        repository[token] = template
        logger().info("RestTemplate registration took ${watch.time}ms")
        return template
    }

    private fun buildRestTemplate(token: String): RestTemplate {
        val templateHandler = RootUriTemplateHandler("$baseEndpoint/bot$token")
        return RestTemplateBuilder()
                .uriTemplateHandler(templateHandler)
                .build()
    }

    private fun String.anonymize() = "...${this.takeLast(5)}"
}