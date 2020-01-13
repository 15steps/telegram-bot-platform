package cf.moviebot.shared.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JacksonUtils {
    val snakeCaseMapper = ObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.SnakeCaseStrategy()
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        registerKotlinModule()
    }
}