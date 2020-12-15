package day9.part2

import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val target = BigInteger.valueOf(258585477)

    println(findSet(input, target))
}

fun findSet(input: List<BigInteger>, target: BigInteger): BigInteger {
    input.forEachIndexed { index, bigInteger ->
        var number = bigInteger
        var range = mutableListOf<BigInteger>(bigInteger)
        for (i in index + 1 until input.size) {
            range.add(input[i])
            number += input[i]
            if (number == target) {
                range.sort()
                val min = range.first()
                val max = range.last()
                return min + max
            } else if (number > target) {
                break
            }
        }
    }

    return BigInteger.ZERO
}

fun readInput(fileName: String): List<BigInteger> {
    var result = mutableListOf<BigInteger>()
    File(fileName).bufferedReader().forEachLine {
        result.add(it.toBigInteger())
    }
    return result
}