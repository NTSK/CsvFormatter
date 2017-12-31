import java.io.File

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val filePath = "resources/item.csv"
        val content = FileLoader(File(filePath).also {
            if (!it.exists()) {
                throw IllegalArgumentException("$filePath にファイルがありません")
            }
        }).load() ?: throw IllegalArgumentException("ファイルの中身がありません")

        val formatter = TextFormatter(content)
        val writer = StdWriter()
        writer.write(formatter)
    }
}