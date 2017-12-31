interface Formatter {
    val data: String
    fun format(): String
}

class TextFormatter(override val data: String): Formatter {
    override fun format(): String = data
}