package day7.part1


import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = readInput(args[0])

    val searchFor = "shiny gold bag"

    val outerBags = mutableSetOf<String>()
    val bagQueue = ArrayDeque<String>()
    bagQueue.push(searchFor)
    while (bagQueue.isNotEmpty()) {
        val innerBag = bagQueue.pop()
        input.filterValues { bags ->
            bags.any { it.bag == innerBag }
        }.keys.forEach { bag ->
            if (outerBags.add(bag))
                bagQueue.push(bag)
        }
    }
    println("Bags containing at least one $searchFor bag: ${outerBags.size}")


}


fun readInput(fileName: String): Map<String, List<Content>> {
    val rules = mutableMapOf<String, List<Content>>()

    File(fileName).bufferedReader().forEachLine {
        val cleanedInput = it
            .replace(".", "")
            .replace("bags", "bag")
        val input = cleanedInput.split("contain")
        val mainBag = input[0].trim()
        val containingBags = input[1].split(',')
        val bags = containingBags.mapNotNull {
            if (it.contains("no other bag")) return@mapNotNull null
            else {
                val scanner = Scanner(it)
                val number = scanner.nextInt()
                val bag = scanner.nextLine().trim()
                Content(number, bag)
            }
        }

        rules.put(mainBag, bags)
    }
    return rules
}

data class Content(
    val amount: Int,
    val bag: String
)