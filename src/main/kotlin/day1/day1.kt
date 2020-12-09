package day1
import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])
    val result = sumTo3(input, 2020)
    println(result)
    println(multiplyList(result))
}

fun sumTo(list: List<Int>, sum: Int): List<Int> {
    val set = list.toSet()
    set.forEach {
        val remainder = sum - it
        if (set.contains(remainder)) {
            return listOf(remainder, it)
        }
    }
    return emptyList()
}

fun sumTo3(list: List<Int>, sum: Int): List<Int> {
    list.forEachIndexed { index, item ->
        val remainder = sum - item
        val subList = list.subList(0, index)
        val parts = sumTo(subList, remainder)
        if(parts.isNotEmpty()){
            val result = mutableListOf<Int>(item)
            result.addAll(parts)
            return result
        }
    }
    return emptyList()
}

fun multiplyList(list: List<Int>): Int {
    var result = 1
    list.forEach {
        result = result * it
    }
    return result
}

fun readInput(fileName: String): List<Int> {
    val output = mutableListOf<Int>()
    File(fileName).bufferedReader().forEachLine {
        output.add(it.toInt())
    }
    return output
}