package cf.moviebot.configuration

import cf.moviebot.shared.util.JacksonUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper {
        return JacksonUtils.snakeCaseMapper
    }
}

