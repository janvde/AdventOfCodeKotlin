package day8

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val set = mutableSetOf<Int>()

    fun execute(program: List<Instruction>, line: Int): Int {
        val instruction = program[line]
        println(instruction)

        if(set.contains(line)) return 0
        set.add(line)
        return when (instruction.type) {
            "nop" -> 0 + execute(program, line + 1)
            "acc" -> instruction.value + execute(program, line + 1)
            "jmp" -> 0 + execute(program, line + instruction.value)
            else -> 0
        }
    }

    val output = execute(input, 0)
    println("value of accumulator = $output")
}




fun readInput(fileName: String): List<Instruction> {
    val result = mutableListOf<Instruction>()

    File(fileName).bufferedReader().forEachLine {
        val scanner = Scanner(it)
        val type = scanner.next()
        val value = scanner.nextInt()

        result.add(Instruction(type, value))
    }
    return result
}

data class Instruction(
    val type: String,
    val value: Int
)