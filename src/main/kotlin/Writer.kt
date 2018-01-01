import java.io.File

interface Writer {
    // 出力データは確実に存在する必要があるので、Non-Nullとする
    fun write(formatter: Formatter)
}

class StdWriter : Writer {
    override fun write(formatter: Formatter) {
        println(formatter.format())
    }
}

class FileWriter(private val file: File) : Writer {
    init {
        require(file.parentFile?.exists() ?: true)
    }

    override fun write(formatter: Formatter) {
        file.createNewFile()
        file.printWriter().use {
            it.println(formatter.format())
        }
    }
}