package day3.part2

import java.io.File
import java.math.BigInteger

//Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:
//
//Right 1, down 1.
//Right 3, down 1. (This is the slope you already checked.)
//Right 5, down 1.
//Right 7, down 1.
//Right 1, down 2.
//In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these produce the answer 336.
//
//What do you get if you multiply together the number of trees encountered on each of the listed slopes?
fun main(args: Array<String>) {
    val input = readInput(args[0])


    val slope1 = countTrees(input, 1, 1)
    val slope2 = countTrees(input, 3, 1)
    val slope3 = countTrees(input, 5, 1)
    val slope4 = countTrees(input, 7, 1)
    val slope5 = countTrees(input, 1, 2)

    val trees = listOf<Int>(slope1, slope2, slope3, slope4, slope5)
    val multiply = multiplyList(trees)
    println(trees)

    println("$multiply multiplied trees")

}

fun countTrees(input: List<List<Boolean>>, right: Int, down: Int): Int {
    val rowLength = input[0].size

    //initialization
    var row = 0
    var position = 0
    var treeCount = 0

    while (row < input.size - 1) {
        val newPosition = (position + right) % rowLength
        position = newPosition

        row = row + down
        val hasTree = input[row][newPosition]
        if (hasTree) treeCount++
    }

    return treeCount
}

//number can be big, so use biginteger
fun multiplyList(list: List<Int>): BigInteger {
    var result = BigInteger.ONE
    list.forEach {
        result = result.multiply(BigInteger.valueOf(it.toLong()))
    }
    return result
}


fun readInput(fileName: String): List<List<Boolean>> {
    val output = mutableListOf<List<Boolean>>()
    File(fileName).bufferedReader().forEachLine {
        val list = it.map {
            when (it) {
                '#' -> true
                else -> false
            }
        }
        output.add(list)
    }
    return output
}