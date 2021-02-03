package day13.part2

import java.io.File


fun main(args: Array<String>) {

    val input = readInput(args[0])
    val ids = input.second

    val numbers = mutableListOf<Long>()
    val remainders = mutableListOf<Long>()

    ids.forEachIndexed { index, id ->
        if (id != "x") {
            val long = id.toLong()
            numbers.add(long)
            remainders.add(long - index)
        }
    }

    val result = chineseRemainder(numbers, remainders)
    println(result)
}
//https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
/* returns x where (a * x) % b == 1 */
fun multInv(a: Long, b: Long): Long {
    if (b == 1L) return 1
    var aa = a
    var bb = b
    var x0 = 0L
    var x1 = 1L
    while (aa > 1L) {
        val q = aa / bb
        var t = bb
        bb = aa % bb
        aa = t
        t = x0
        x0 = x1 - q * x0
        x1 = t
    }
    if (x1 < 0L) x1 += b
    return x1
}

//https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
fun chineseRemainder(numbers: List<Long>, remainders: List<Long>): Long {
    val prod: Long = numbers.fold(1) { acc, i -> acc * i }
    var sum = 0L
    for (i in 0 until numbers.size) {
        val p = prod / numbers[i]
        sum += remainders[i] * multInv(p, numbers[i]) * p
    }
    return sum % prod
}


//return <timestamp, List<bus id>
fun readInput(fileName: String): Pair<Int, List<String>> {
    val reader = File(fileName).bufferedReader()

    val timestamp = reader.readLine().toInt()
    val ids = reader.readLine().split(',')
    return Pair(timestamp, ids)
}