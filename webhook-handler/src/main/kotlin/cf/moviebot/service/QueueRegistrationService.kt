package cf.moviebot.service

import cf.moviebot.logging.logger
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.Queue
import org.springframework.stereotype.Service

@Service
class QueueRegistrationService(
    private val admin: AmqpAdmin,
    private val webhookExchange: Exchange
) {
    private val queueCache = mutableMapOf<String, Boolean>()

    fun registerBotQueue(name: String): String {
        val fullQueueName = buildQueueName(name)
        if (queueCache[name] == true) {
            logger().warn("Queue has already been declared. queue=$name")
        } else {
            val exchangeName = webhookExchange.name
            val queue= buildQueue(fullQueueName)
            admin.declareQueue(queue)
            val biding = Binding(
                fullQueueName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                name,
                mapOf()
            )
            admin.declareBinding(biding)
            queueCache[name] = true

            logger().info("Queue bound to exchange. key=$name, exchange=$exchangeName, queue=$fullQueueName")
        }
        return fullQueueName
    }

    private fun buildQueue(name: String) =
        Queue(name, false, false, false, mapOf())

    private fun buildQueueName(name: String) = "$name.webhook.mo"

}