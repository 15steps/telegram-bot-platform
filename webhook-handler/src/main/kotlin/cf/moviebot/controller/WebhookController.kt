package cf.moviebot.controller

import cf.moviebot.domain.Update
import cf.moviebot.logging.logger
import cf.moviebot.service.TelegramAPIService
import cf.moviebot.service.UpdatePublisherService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("webhook")
class WebhookController(
    private val telegramService: TelegramAPIService,
    private val updatePublisher: UpdatePublisherService
) {
    private val queues = mutableMapOf<String, String>()

    @GetMapping
    fun index() = "It works"

    @PostMapping(value = ["/{botId}"])
    suspend fun handleMO(@PathVariable botId: String, @RequestBody updates: List<Update>) {
        logger().info("Received MO. botId={}, updates={}", botId, updates)
        coroutineScope {
            launch {
                val userName = queues[botId] ?: telegramService.getMe(botId)?.userName
                updatePublisher.publish(userName, updates)
                logger().info("Published update. queue=$userName")
            }
        }
    }
}