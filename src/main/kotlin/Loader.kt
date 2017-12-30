import java.io.File

interface Loader {
    fun load(): String?
}

class FileLoader(private val file: File): Loader {
    init {
        require(file.exists())
    }

    override fun load(): String? = file.readText()
}