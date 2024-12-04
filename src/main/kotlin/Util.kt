package org.example

object Util {
    fun readFileLineByLine(fileName: String): List<String> {
        val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
        require(inputStream != null)
        return inputStream.bufferedReader().readLines()
    }
}