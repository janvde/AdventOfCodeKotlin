package day5.part1

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0])


    var max = 0
    input.forEach {
        //read row bits and convert to binary
        val row = it.slice(0..6).replace("F", "0").replace("B", "1")
        //read column bits and convert to binary
        val column = it.slice(7..9).replace("R", "1").replace("L", "0")

        //convert to integer
        val rowNumber = Integer.parseInt(row, 2)
        val columnNumber = Integer.parseInt(column, 2)

        val seatId = (rowNumber*8)+columnNumber
        println("$it: row $rowNumber, column $columnNumber, seat ID $seatId")

        if(seatId > max) max = seatId
    }

    println("highest seatID = $max")

}


fun readInput(fileName: String): List<String> {
    return File(fileName).bufferedReader().readLines()
}