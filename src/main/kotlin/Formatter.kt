import com.google.gson.Gson
import com.google.gson.GsonBuilder

interface Formatter {
    // 変換されたデータは必ず存在するのでNon-Nullとする
    val items: List<Row>

    fun format(): String
}

enum class Format {
    JSON,
    MARKDOWN,
    TEXT,
    TABLE
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

class JSONFormatter(override val items: List<Row>, private val gson: Gson = GsonBuilder().create()) : Formatter {
    override fun format(): String = gson.toJson(items)
}

class MarkdownTableFormatter(override val items: List<Row>) : Formatter {
    override fun format(): String = items.joinToString(
            separator = System.getProperty("line.separator")) {
        when (it) {
            is Row.Header -> {
                val (header, line) =
                        it.columns.fold(StringBuilder("|") to StringBuilder("|"),
                                { (header, line), column -> (header.append("$column")) to (line.append("---|")) })
                header.append(System.getProperty("line.separator")).append(line)
            }
            is Row.Item -> {
                val r = "|${it.id}|${it.name}|"
                if (it.price != null) {
                    r + "${it.price}"
                } else {
                    r + "|"
                }
            }
        }
    }
}
