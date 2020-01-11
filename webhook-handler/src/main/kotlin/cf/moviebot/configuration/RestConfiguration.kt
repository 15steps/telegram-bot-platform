package cf.moviebot.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RootUriTemplateHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfiguration(
    @Value("\${telegram.endpoint}") private val telegramEndpoint: String
) {
    @Bean
    fun botAPIRestTemplate(): RestTemplate {
        val templateHandler = RootUriTemplateHandler(telegramEndpoint)
        return RestTemplateBuilder()
                .uriTemplateHandler(templateHandler)
                .build()
    }
}

