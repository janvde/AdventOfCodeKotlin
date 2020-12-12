package day5.part1

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])


    var seats = mutableListOf<Seat>()
    var max = 0
    input.forEach {
        //read row bits and convert to binary
        val row = it.slice(0..6).replace("F", "0").replace("B", "1")
        //read column bits and convert to binary
        val column = it.slice(7..9).replace("R", "1").replace("L", "0")

        //convert to integer
        val rowNumber = Integer.parseInt(row, 2)
        val columnNumber = Integer.parseInt(column, 2)

        val seatId = (rowNumber * 8) + columnNumber
        seats.add(
            Seat(rowNumber, columnNumber, seatId)
        )
        println("$it: row $rowNumber, column $columnNumber, seat ID $seatId")

        if (seatId > max) max = seatId
    }

    println("highest seatID = $max")
    println("your seat: ${findMissingSeat(seats)}")
}

fun findMissingSeat(seats: List<Seat>): Int {
    val set = seats.map { it.id }

    set.forEach {
        val nextIsMissing = !set.contains(it + 1)
        val secondNextIsPresent = set.contains(it + 2)

        if (nextIsMissing && secondNextIsPresent) {
            return it + 1
        }
    }

    return 0
}


fun readInput(fileName: String): List<String> {
    return File(fileName).bufferedReader().readLines()
}

data class Seat(
    val row: Int,
    val column: Int,
    val id: Int
)