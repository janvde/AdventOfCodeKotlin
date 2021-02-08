package day15.part1

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val turns = 30000000
    val memory = input.mapIndexed { index, number -> number to index }.toMap().toMutableMap()
    val numbers = mutableListOf<Int>()
    numbers.addAll(input)


    /**
     * If that was the first time the number has been spoken, the current player says 0.
     * Otherwise, the number had been spoken before;
     * the current player announces how many turns apart the number is from when it was previously spoken.
     */
    var next = 0
    for (i in input.size until turns) {
        numbers.add(next)
        //println("${i+1} $next")
        val lastNumber = memory[next] ?: i
        memory[next] = i
        next = i - lastNumber
    }

    println(numbers.last())
}

fun readInput(fileName: String): List<Int> {
    return File(fileName).bufferedReader().readLine().split(",").map { it.toInt() }
}