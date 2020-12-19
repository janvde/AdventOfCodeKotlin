package day12.part1

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = readInput(args[0])

    var east = 0
    var north = 0

    var currentDirection = "E"

    input.forEach {
        val direction = it.first
        val value = it.second
        when (direction) {
            "N" -> north += value
            "S" -> north -= value
            "E" -> east += value
            "W" -> east -= value
            "L" -> {
                currentDirection = getDirection(currentDirection, false, value)
            }
            "R" -> {
                currentDirection = getDirection(currentDirection, true, value)
            }
            "F" -> {
                when(currentDirection){
                    "N" -> north += value
                    "S" -> north -= value
                    "E" -> east += value
                    "W" -> east -= value
                }
            }
        }
        println("$direction$value -> north=$north, east=$east dir=$currentDirection")
    }

    val manhattanDistance = abs(east) + abs(north)
    println("manhatatan distance = $manhattanDistance")
}

fun getDirection(direction: String, right: Boolean, degrees: Int): String {
    val directions = listOf<String>("N", "E", "S", "W")
    val degreeIndex = if (right) (degrees / 90) else -(degrees / 90) + 4 //+4 to keep positive
    val newDirectionIndex = (directions.indexOf(direction) + degreeIndex) % 4

    return directions[newDirectionIndex]
}


fun readInput(fileName: String): List<Pair<String, Int>> {
    var result = mutableListOf<Pair<String, Int>>()
    File(fileName).bufferedReader().forEachLine {
        val direction = it.substring(0, 1)
        val value = it.substring(1, it.length).toInt()
        result.add(Pair(direction, value))
    }
    return result
}