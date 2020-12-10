package day3.part1

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val rowLength = input[0].size

    //initialization
    var row = 0
    var position = 0
    var treeCount = 0

    while (row < input.size -1){
        val newPosition = (position + 3) % rowLength
        position = newPosition

        row++
        val hasTree = input[row][newPosition]
        if(hasTree) treeCount++

        //println("($newPosition, $row) -> $hasTree")
    }

    println("$treeCount trees")

}


fun readInput(fileName: String): List<List<Boolean>> {
    val output = mutableListOf<List<Boolean>>()
    File(fileName).bufferedReader().forEachLine {
        val list = it.map {
            when(it){
                '#' -> true
                else -> false
            }
        }
        output.add(list)
    }
    return output
}