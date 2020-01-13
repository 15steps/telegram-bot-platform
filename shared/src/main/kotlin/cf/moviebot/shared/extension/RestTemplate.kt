package cf.moviebot.shared.extension

import cf.moviebot.shared.domain.ApiResult
import cf.moviebot.shared.util.JacksonUtils
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

val mapper = JacksonUtils.snakeCaseMapper
val typeFactory = mapper.typeFactory!!

inline fun <reified T> RestTemplate.getApiResult(url: String): ApiResult<T>? {
    val response = getForEntity<String>(url).body
    val type = typeFactory.constructParametricType(ApiResult::class.java, T::class.java)
    return mapper.readValue(response, type)
}