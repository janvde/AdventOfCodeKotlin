package day10.part2

import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val input = readInput(args[0]).toMutableList()
    input.add(0)
    val adapters = input.sorted().toMutableList()
    adapters.add(adapters.last()+3)

    println(adapters)

    var deltas = mutableListOf<Int>()
    adapters.forEachIndexed { index, integer ->
        if(index > 0){
            deltas.add(integer - adapters[index - 1])
        }
    }

    //we could calculate the number of combinations using backtracking
    //but since in the input there's a max of 4 consecutive 1's,
    //just use a static combinattion map

    //find consecutive 1's, because only that makes combinations
    var ones = 0
    var consecutiveOnes = mutableListOf<Int>()
    deltas.forEach {
        if(it == 1){
            ones++
        } else {
            if(ones > 0)consecutiveOnes.add(ones)
            ones = 0
        }
    }

    var combinations = BigInteger.ONE
    consecutiveOnes.forEach {
        combinations*= BigInteger.valueOf(getCombinations(it).toLong())
    }
    println(combinations)
}

private fun getCombinations(oneDeltas: Int): Int {
    val deltaToCombinations = listOf<Int>(0, 1, 2, 4, 7)
    return deltaToCombinations[oneDeltas]

}

fun readInput(fileName: String): List<Int> {
    var result = mutableListOf<Int>()
    File(fileName).bufferedReader().forEachLine {
        result.add(it.toInt())
    }
    return result
}