package day11.part2

import java.io.File

fun main(args: Array<String>) {
    val input = readInput(args[0]).map { it.toTypedArray() }.toTypedArray()
    val rows = input.size
    val columns = input[0].size

    /**
     * If a seat is empty (L) and there are no occupied seats adjacent to it,
     * the seat becomes occupied.
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied,
     * the seat becomes empty.
     */
    var matrix = input.copyOf()
//    getAdjacentOccupiedSeats(matrix, 9,0)
    while (true) {
        var output = Array(rows) { Array(columns) { '.' } }

        matrix.forEachIndexed { row, rowItems ->
            matrix[row].forEachIndexed { column, char ->
                if (isEmpty(matrix, row, column) && getAdjacentOccupiedSeats(matrix, row, column) == 0) {
                    output[row][column] = '#'
                } else if (isOccupied(matrix, row, column) && getAdjacentOccupiedSeats(matrix, row, column) >= 5) {
                    output[row][column] = 'L'
                } else {
                    output[row][column] = char
                }
            }
        }

        if (isEqual(matrix, output)) {
            println("occupied seats: ${countOccupiedSeats(output)}")
            break
        } else {
            //printSeats(output)
            matrix = output.copyOf()
        }
    }
}

fun isOccupied(seats: Array<Array<Char>>, row: Int, column: Int): Boolean {
    return seats[row][column] == '#'
}

fun getAdjacentOccupiedSeats(seats: Array<Array<Char>>, row: Int, column: Int): Int {
    val directions = listOf<Int>(-1, 0, 1)

    var adjacentSeats = 0
    directions.forEach { x ->
        directions.forEach { y ->
            var checkRow = row + x
            var checkColumn = column + y

            if (!(x == 0 && y == 0)) { //this seat
                while (true) {
                    if (checkRow < 0 || checkRow >= seats.size  || checkColumn < 0 || checkColumn >= seats[0].size) {
                        break
                    }
//                    println("check seat [$checkRow,$checkColumn] -> ${seats[checkRow][checkColumn]}")
                    if (isOccupied(seats, checkRow, checkColumn)) {
                        adjacentSeats++
                        break
                    } else if (isEmpty(seats, checkRow, checkColumn)) {
                        break
                    } else {
                        checkRow += x
                        checkColumn += y
                    }
                }
            }
        }
    }


    return adjacentSeats
}

fun isEmpty(seats: Array<Array<Char>>, row: Int, column: Int): Boolean {
    return seats[row][column] == 'L'
}

fun countOccupiedSeats(seats: Array<Array<Char>>): Int {
    var counter = 0
    seats.forEach {
        it.forEach {
            if (it == '#') counter++
        }
    }

    return counter
}

fun printSeats(seats: Array<Array<Char>>) {
    seats.forEach {
        it.forEach {
            print(it)
        }
        println()
    }
    println()
}

fun <T> isEqual(first: Array<Array<T>>, second: Array<Array<T>>): Boolean {
    if (first == second) return true
    if (first == null || second == null) return false
    if (first.size != second.size) return false
    for (i in first.indices) {
        if (first[i].size != second[i].size) return false
        for (j in first[i].indices) {
            if (!first[i][j]?.equals(second[i][j])!!) return false
        }
    }
    return true
}


fun readInput(fileName: String): List<List<Char>> {
    var result = mutableListOf<MutableList<Char>>()
    File(fileName).bufferedReader().forEachLine {
        result.add(it.toCharArray().toMutableList())
    }
    return result
}