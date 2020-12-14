package day6

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = readInput(args[0])

    var totalCount = 0
    input.forEach {
        val yesCount = findYesses(it)
        totalCount += yesCount
    }

    println("sum of countst = $totalCount")
}

fun findYesses(answers: List<String>): Int {
    val set = mutableSetOf<Char>()
    answers.forEach {
        it.forEach {
            set.add(it)
        }
    }

    return set.size
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