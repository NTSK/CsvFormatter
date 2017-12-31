interface Parser {
    fun parse(content: String): List<Row>
    class IllegalFormatException(override val message: String): Exception(message)
}