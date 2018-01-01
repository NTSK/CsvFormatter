import java.io.File

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val filePath = "resources/item.csv"
        val content = FileLoader(File(filePath).also {
            if (!it.exists()) {
                throw IllegalArgumentException("$filePath にファイルがありません")
            }
        }).load()?.let(CSVParser()::parse)
                ?: throw IllegalArgumentException("ファイルの中身がありません")

        val format = enumValueOf<Format>("JSON")

        val formatter = when(format) {
            Format.JSON -> JSONFormatter(content)
            Format.MARKDOWN -> MarkdownFormatter(content)
            Format.TEXT -> TextFormatter(content)
        }
        val writer = StdWriter()
        writer.write(formatter)
    }
}