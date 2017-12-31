interface Formatter {
    // 変換されたデータは必ず存在するのでNon-Nullとする
    val items: List<Row>
    fun format(): String
}

class TextFormatter(override val items: List<Row>): Formatter {
    override fun format(): String = items.toString()
}