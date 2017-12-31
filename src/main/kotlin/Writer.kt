interface Writer {
    // 出力データは確実に存在する必要があるので、Non-Nullとする
    fun write(formatter: Formatter)
}

class StdWriter: Writer {
    override fun write(formatter: Formatter) {
        println(formatter.format())
    }
}