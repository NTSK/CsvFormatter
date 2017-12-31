interface Parser {
    fun parse(content: String): List<Row>
    class IllegalFormatException(override val message: String) : Exception(message)
}

class CSVParser : Parser {
    override fun parse(content: String): List<Row> {
        val rows = content.split(System.getProperty("line.separator"))

        val header = rows.getOrNull(0)?.let { Row.Header(it.split(",")) }
                ?: throw Parser.IllegalFormatException("1行目にヘッダーがありません")

        val items = rows.drop(1).mapIndexed { index, rowString ->
            val cells = rowString.split(",")
            val id = cells.getOrElse(0, { throw Parser.IllegalFormatException("${index + 1}にidがありません") })
                    .toLongOrThrow { "${index + 1}が数値ではありません" }
            val name = cells.getOrElse(1, { throw Parser.IllegalFormatException("${index + 1}にnameがありません") })
            val price = cells.getOrNull(2)?.toLongOrNull()
            Row.Item(id, name, price)
        }
        return listOfNotNull(header) + items
    }

    private inline fun String.toLongOrThrow(radix: Int = 10, lazyMessage: (String) -> String): Long {
        try {
            return toLong(radix)
        } catch (e: NumberFormatException) {
            throw Parser.IllegalFormatException(lazyMessage(this))
        }
    }
}