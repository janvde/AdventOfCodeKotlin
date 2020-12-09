package day2.part1

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val result = input.count { password ->
        val count = password.code.count { it.toString() == password.char }
        count >= password.minimum && count <= password.maximum
    }

    println(result)
}


fun readInput(fileName: String): List<Password> {
    val output = mutableListOf<Password>()
    File(fileName).bufferedReader().forEachLine {
        val line = it.replace("-", " ").replace(":", "")
        val scanner = Scanner(line)
        val minimum = scanner.nextInt()

        val maximum = scanner.nextInt()
        val char = scanner.next()
        val code = scanner.next()


        val password = Password(minimum, maximum, char, code)
        output.add(password)
    }
    return output
}