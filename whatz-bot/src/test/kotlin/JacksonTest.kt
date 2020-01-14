import cf.moviebot.shared.domain.ApiResult
import cf.moviebot.shared.domain.Message
import cf.moviebot.shared.domain.Update
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test

class JacksonTest {

    @Test
    fun updateDeserialization() {
        val mapper = ObjectMapper().apply {
            registerModule(KotlinModule())
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
        val json = """
            {"updateId":874199207,"message":{"messageId":25,"text":"kkkkk","from":{"id":159349529,"firstName":"Wellington","lastName":"Felix","username":"ufelix","languageCode":"en","bot":false},"date":1578959854},"editedMessage":null,"channelPost":null,"editedChannelPost":null,"inlineQuery":null}
            """.trimIndent()
        val update = mapper.readValue<Update>(json)
        assert(update.updateId == 874199207L)
    }

    @Test
    fun apiResultDeserialization() {
        val mapper = ObjectMapper().apply {
            propertyNamingStrategy = PropertyNamingStrategy.SnakeCaseStrategy()
            registerModule(KotlinModule())
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
        val json = """{"ok":true,"result":{"message_id":35,"from":{"id":893448444,"is_bot":true,"first_name":"zapzap","username":"whatz_bot"},"chat":{"id":159349529,"first_name":"Wellington","last_name":"Felix","username":"ufelix","type":"private"},"date":1578968005,"text":"Select the following link to open Whatsapp chat: https://api.whatsapp.com/send?phone=hello","entities":[{"offset":49,"length":41,"type":"url"}]}}"""
        val type = mapper.typeFactory.constructParametricType(ApiResult::class.java, Message::class.java)
        val result = mapper.readValue<ApiResult<Message>?>(json, type)
        println(result)
        assert(result?.ok == true)
        assert(result?.result?.messageId == 35L)
    }

}