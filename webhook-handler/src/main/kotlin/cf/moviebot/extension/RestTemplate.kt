package cf.moviebot.extension

import cf.moviebot.domain.ApiResult
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

val mapper = ObjectMapper()
val typeFactory = mapper.typeFactory!!

inline fun <reified T> RestTemplate.getForEntity(url: String): ApiResult<T>? {
    val response = getForEntity<String>(url).body
    val type = typeFactory.constructParametricType(ApiResult::class.java, T::class.java)
    return mapper.readValue(response, type)
}