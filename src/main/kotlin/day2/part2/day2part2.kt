package day2.part2

import java.io.File
import java.util.*

/**
Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.

Given the same example list from above:

1-3 a: abcde is valid: position 1 contains a and position 3 does not.
1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
How many passwords are valid according to the new interpretation of the policies?
 */
fun main(args: Array<String>) {
    val input = readInput(args[0])

    val result = input.count { password ->
        val validFirstPosition = password.code[password.firstPosition - 1].toString() == password.char
        val validSecondPosition = password.code[password.secondPosition - 1].toString() == password.char
        validFirstPosition.xor(validSecondPosition) //either 1 is true
    }

    println(result)
}


fun readInput(fileName: String): List<Password> {
    val output = mutableListOf<Password>()
    File(fileName).bufferedReader().forEachLine {
        val line = it.replace("-", " ").replace(":", "")
        val scanner = Scanner(line)
        val minimum = scanner.nextInt()

        val maximum = scanner.nextInt()
        val char = scanner.next()
        val code = scanner.next()


        val password = Password(minimum, maximum, char, code)
        output.add(password)
    }
    return output
}