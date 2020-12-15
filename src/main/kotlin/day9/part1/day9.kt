package day9.part1

import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val preambleSize = 25
    for (i in preambleSize until input.size){
        val item = input[i]
        val preamble = input.subList(i-preambleSize, i)
        if(!isSplittableBy(item, preamble)){
            println("first number which is not the sum of two of the previous numbers: $item ")
        }
    }

}

fun isSplittableBy(number: BigInteger, preamble: List<BigInteger>): Boolean{
    val set = preamble.toSet()
    preamble.forEach {
        val remainder = number - it
        if(set.contains(remainder) && remainder != it) return true
    }

    return false
}


fun readInput(fileName: String): List<BigInteger> {
    var result = mutableListOf<BigInteger>()
    File(fileName).bufferedReader().forEachLine {
        result.add(it.toBigInteger())
    }
    return result
}