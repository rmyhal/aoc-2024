import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach { ids ->
            val (first, second) = ids.split("   ")
            left.add(first.toInt())
            right.add(second.toInt())
        }
        val leftSorted = left.sorted()
        val rightSorted = right.sorted()
        var answer = 0
        for (i in leftSorted.indices) {
            answer += (leftSorted[i] - rightSorted[i]).absoluteValue
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableMapOf<Int, Int>()
        input.forEach { ids ->
            val (first, second) = ids.split("   ")
            left.add(first.toInt())
            val key = second.toInt()
            val existing = right.getOrElse(key) { 0 }
            right[key] = existing + 1
        }
        var answer = 0
        left.forEach { l ->
            answer += l * right.getOrElse(l) { 0 }
        }
        return answer
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("day_1")
    part1(input).println()
    part2(input).println()
}
