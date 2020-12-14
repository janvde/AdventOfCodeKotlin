package day6.part2

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = readInput(args[0])

    var totalCount = 0
    input.forEach {
        val yesCount = getIntersect(it)
        totalCount += yesCount
        println(yesCount)
    }

    println("sum of counts = $totalCount")
}

//find the intersection of all chars
fun getIntersect(answers: List<String>): Int {
    var result = mutableSetOf<Char>()
    result.addAll(answers[0].toCharArray().toList())
    answers.forEach {
        val chars = it.toCharArray()
        result = result.intersect(chars.toList()).toMutableSet()
    }

    return result.size
}


fun readInput(fileName: String): List<List<String>> {
    var group = mutableListOf<List<String>>()
    var answers = mutableListOf<String>()
    File(fileName).bufferedReader().forEachLine {
        if (it.isBlank()) {
            group.add(answers)
            answers = mutableListOf<String>() //reset
        } else {
            val scanner = Scanner(it)
            while (scanner.hasNext()) {
                val item = scanner.next()
                answers.add(item)
            }
        }

    }
    group.add(answers)
    return group
}