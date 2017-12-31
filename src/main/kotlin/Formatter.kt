interface Formatter {
    // 変換されたデータは必ず存在するのでNon-Nullとする
    val items: List<Row>

    fun format(): String
}

class TextFormatter(override val items: List<Row>) : Formatter {
    override fun format(): String = items.toString()
}

class MarkdownFormatter(override val items: List<Row>) : Formatter {
    override fun format(): String {
        return items.mapNotNull { it as? Row.Item }.joinToString(separator = "") {
            if (it.price == null) {
                """
- ${it.id}
  - ${it.name}
                """
            } else {
                """
- ${it.id}
  - ${it.name}
  - ${it.price}
                """
            }
        }
    }
}