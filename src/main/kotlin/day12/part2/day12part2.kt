package day12.part2

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = readInput(args[0])

    var waypoint = Position( 10, 1) //initial waypoint
    var shipLocation = Position(0,0)

    input.forEach {
        val direction = it.first
        val value = it.second
        when(direction){
            "N" -> waypoint.north += value
            "S" -> waypoint.north -= value
            "E" -> waypoint.east += value
            "W" -> waypoint.east -= value
            "L" -> waypoint = rotate(waypoint, false, value)
            "R" -> waypoint = rotate(waypoint, true, value)
            "F" -> {
                shipLocation.apply {
                    east += waypoint.east * value
                    north += waypoint.north * value
                }
            }

        }
    }
    val manhattanDistance = abs(shipLocation.east) + abs(shipLocation.north)
    println("manhattan distance = $manhattanDistance")
}

//simplified matrix rotation
fun rotate(waypoint: Position, clockwise: Boolean, degrees: Int) : Position{
    if(clockwise){
        //(𝑥,𝑦) to (𝑦,−𝑥)
        val rotated = Position(waypoint.north, waypoint.east *-1)
        if(degrees > 90){
            return rotate(rotated, clockwise, degrees-90)
        }
        return rotated
    } else {
        // (𝑥,𝑦) to (−𝑦,𝑥)
        val rotated = Position(waypoint.north*-1, waypoint.east)
        if(degrees > 90){
            return rotate(rotated, clockwise, degrees-90)
        }
        return rotated
    }
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

data class Position(var east: Int, var north: Int)