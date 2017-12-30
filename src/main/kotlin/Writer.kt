interface Writer {
    fun write(formatter: Formatter)
}

class stdWriter: Writer {
    override fun write(formatter: Formatter) {
        println(formatter.format())
    }
}