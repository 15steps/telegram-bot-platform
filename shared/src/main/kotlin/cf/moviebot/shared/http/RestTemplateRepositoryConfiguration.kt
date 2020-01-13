package cf.moviebot.shared.http

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class RestTemplateRepositoryConfiguration {
    @Bean
    fun restTemplateRepository(@Value("\${telegram.endpoint}") endpoint: String) =
        RestTemplateRepository(endpoint)
}