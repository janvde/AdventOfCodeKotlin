package day14.part1

import java.io.File

val memory = mutableMapOf<Long, Long>()

fun main(args: Array<String>) {
    val input = readInput(args[0])

    var mask = "" //initial value

    input.forEach {
        if (it.startsWith("mask")) {
            mask = it.split(" ").last()
        } else {
            val regex = "(?<=\\[).+?(?=\\])".toRegex()
            val address = regex.find(it)!!.value.toLong()
            val value = it.split(" ").last().toLong()

            val maskedValue = applyMask(mask, value)
            memory[address] = maskedValue
        }

    }
    println(sumOfMemory())
}

fun applyMask(mask: String, value: Long): Long {
    var maskedValue = value
    mask.forEachIndexed { index, char ->
        when (char) {
            '1' -> {
                val singleBitMask = 1L shl (36 - index - 1) //bit shift Long 1 to index position
                maskedValue = maskedValue or singleBitMask //bitwise or
            }
            '0' -> {
                val singleBitMask = 1L shl (36 - index - 1) //bit shift Long 1 to index position
                maskedValue = maskedValue and singleBitMask.inv() //bitwise and `invert
            }
        }
    }

    return maskedValue

}

fun sumOfMemory(): Long {
    var sum = 0L
    memory.forEach { sum += it.value }
    return sum
}


fun readInput(fileName: String): List<String> {
    return File(fileName).bufferedReader().lineSequence().toList()
}