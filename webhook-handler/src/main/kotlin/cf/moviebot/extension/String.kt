package cf.moviebot.extension


fun String.toBotUri(method: String) = "/bot$this/$method"