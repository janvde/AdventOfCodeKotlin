package day4.part1

import java.io.File
import java.util.*

//Count the number of valid passports - those that have all required fields. Treat cid as optional.
//In your batch file, how many passports are valid?
fun main(args: Array<String>) {
    val input = readInput(args[0])


    val count = input.count {
        isValid(it)
    }

    println("number of valid passports = ${count}")
}

fun isValid(passport: List<String>): Boolean {
    return if (passport.size == 8) true
    else if (passport.size == 7) {
        //does it contain the optional 'cid'?
        !passport.any { it.startsWith("cid") }
    } else {
        false
    }
}


fun readInput(fileName: String): List<List<String>> {
    var passports = mutableListOf<List<String>>()
    var passport = mutableListOf<String>()
    File(fileName).bufferedReader().forEachLine {
        if (it.isBlank()) {
            passports.add(passport)
            passport = mutableListOf<String>() //reset
        } else {
            val scanner = Scanner(it)
            while (scanner.hasNext()) {
                val item = scanner.next()
                passport.add(item)
            }
        }

    }
    passports.add(passport)
    return passports
}