package cf.moviebot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebhookHandlerApplication

fun main(args: Array<String>) {
    runApplication<WebhookHandlerApplication>(*args)
}