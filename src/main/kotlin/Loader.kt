import java.io.File

interface Loader {
    // 読み込みの際は文字列を返さない可能性もあるのでNullableとする
    fun load(): String?
}

class FileLoader(private val file: File) : Loader {
    init {
        require(file.exists())
    }

    override fun load(): String? = file.readText()
}