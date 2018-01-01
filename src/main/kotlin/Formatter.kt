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
    TEXT
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

class JSONFormatter(override val items: List<Row>, private val gson : Gson = GsonBuilder().create()) : Formatter {
    override fun format(): String = gson.toJson(items)
}
