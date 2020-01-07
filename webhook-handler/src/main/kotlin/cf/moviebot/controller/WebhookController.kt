package cf.moviebot.controller

import cf.moviebot.logging.logger
import cf.moviebot.service.TelegramAPIService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("webhook")
class WebhookController(
    private val telegramService: TelegramAPIService
) {
    private val queues = mutableMapOf<String, String>()

    @GetMapping
    fun index() = "It works"

    @PostMapping(value = ["/{botId}"])
    suspend fun handleMO(@PathVariable botId: String, @RequestBody body: List<Map<String, Any>>) {
        logger().info("Received MO. botId={}, payload={}", botId, body)
        coroutineScope {
            launch {
                val queueName = queues[botId] ?: telegramService.getMe(botId)?.userName
                logger().info("Publishing update to queue=$queueName")
            }
        }
    }
}