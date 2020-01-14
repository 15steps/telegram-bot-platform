package cf.moviebot.shared.domain

data class Chat(
   val id: Long,
   val type: String? = null,
   val title: String? = null,
   val username: String? = null,
   val firstName: String? = null,
   val lastName: String? = null
)