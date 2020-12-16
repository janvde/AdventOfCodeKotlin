package day10

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])
    val adapters = input.sorted()

    //println(adapters)

    var numberOfOnes = 0
    var numberOfThrees = 1 // step to device

    adapters.forEachIndexed { index, integer ->
        val stepFromPrevious = if(index ==0){
            integer
        } else {
            integer-adapters[index-1]
        }

        when(stepFromPrevious){
            1 -> numberOfOnes++
            3 -> numberOfThrees++
            else -> println("uncounted step size")
        }
    }

    println("number of ones = $numberOfOnes, number of three's = $numberOfThrees")
    println("$numberOfOnes*$numberOfThrees=${numberOfOnes*numberOfThrees}")

}

fun readInput(fileName: String): List<Int> {
    var result = mutableListOf<Int>()
    File(fileName).bufferedReader().forEachLine {
        result.add(it.toInt())
    }
    return result
}