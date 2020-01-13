package cf.moviebot.shared.extension

import cf.moviebot.shared.domain.ApiResult
import cf.moviebot.shared.util.JacksonUtils
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.postForEntity
import javax.swing.text.html.HTML.Tag.U

val mapper = JacksonUtils.snakeCaseMapper
val typeFactory = mapper.typeFactory!!

inline fun <reified T> RestTemplate.getApiResult(url: String): ApiResult<T>? {
    val response = getForEntity<String>(url).body
    val type = typeFactory.constructParametricType(ApiResult::class.java, T::class.java)
    return mapper.readValue(response, type)
}

inline fun <reified U> RestTemplate.postApiResult(url: String, body: Any): ApiResult<U>? {
    val response = postForEntity<String>(url, body).body
    val type = typeFactory.constructParametricType(ApiResult::class.java, U::class.java)
    return mapper.readValue(response, type)
}