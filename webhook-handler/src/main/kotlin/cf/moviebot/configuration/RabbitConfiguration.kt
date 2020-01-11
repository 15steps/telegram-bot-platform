package cf.moviebot.configuration

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration {
    private val webhookExchangeName = "webhook.mo.ex"

    @Bean
    fun jackson2MessageConverter() =
        Jackson2JsonMessageConverter()

    @Bean
    fun amqpAdmin(connectionFactory: ConnectionFactory): AmqpAdmin {
        return RabbitAdmin(connectionFactory)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        jackson2JsonMessageConverter: Jackson2JsonMessageConverter): RabbitTemplate {
        return RabbitTemplate(connectionFactory).apply {
            messageConverter = jackson2JsonMessageConverter
        }
    }

    @Bean
    fun webhookExchange(): Exchange {
        return DirectExchange(webhookExchangeName)
    }
}