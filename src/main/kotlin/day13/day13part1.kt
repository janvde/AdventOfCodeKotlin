package day13

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val timestamp = input.first
    val ids = input.second

    var min = Integer.MAX_VALUE
    var earliestBus = 0
    ids.forEach { id ->
        val earliestTime = timestamp - (timestamp % id) + id
        if (min > earliestTime) {
            min = earliestTime
            earliestBus = id
        }
    }

    val waitTime = min - timestamp
    println("outcome: ${waitTime*earliestBus}")
}

//return <timestamp, List<bus id>
fun readInput(fileName: String): Pair<Int, List<Int>> {
    val reader = File(fileName).bufferedReader()

    val timestamp = reader.readLine().toInt()
    val ids = reader.readLine().split(',').filterNot { it == "x" }.map { it.toInt() }
    return Pair(timestamp, ids)
}