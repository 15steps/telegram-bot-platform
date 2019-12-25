package cf.moviebot.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("webhook")
class WebhookController {
    @GetMapping
    fun index() = "It works"

    @PostMapping
    fun handleMO() {

    }
}