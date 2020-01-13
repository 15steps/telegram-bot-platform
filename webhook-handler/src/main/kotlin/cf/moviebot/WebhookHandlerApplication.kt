package cf.moviebot

import cf.moviebot.shared.http.RestTemplateRepositoryConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(value = [
    RestTemplateRepositoryConfiguration::class
])
@SpringBootApplication
class WebhookHandlerApplication

fun main(args: Array<String>) {
    runApplication<WebhookHandlerApplication>(*args)
}