interface Formatter {
    // 変換されたデータは必ず存在するのでNon-Nullとする
    val data: String
    fun format(): String
}

class TextFormatter(override val data: String): Formatter {
    override fun format(): String = data
}