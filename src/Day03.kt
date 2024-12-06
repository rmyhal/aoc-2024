fun main() {
  val regex = Regex("mul\\((?<d1>\\d+),(?<d2>\\d+)\\)")

  fun part1(input: List<String>): Int {
    var answer = 0
    for (i in input.indices) {
      val line = input[i]
      val sum = regex.findAll(line)
        .map { mul ->
          val (one, two) = mul.value.substringAfter('(').dropLast(1).split(',')
          one.toInt() * two.toInt()
        }
        .sum()

      answer += sum
    }
    return answer
  }

  fun part2(input: List<String>): Int {
    var enabled = true
    return input.sumOf { line ->
      val mulMatches = regex.findAll(line)
      val doMatches = "do\\(\\)".toRegex().findAll(line)
      val dontMatches = "don\'t\\(\\)".toRegex().findAll(line)

      var count = 0
      val sorted = (mulMatches + doMatches + dontMatches).sortedBy { it.range.first }
      sorted.forEach { match ->
        when (match.value.take(3)) {
          "do(" -> enabled = true
          "don" -> enabled = false
          "mul" -> if (enabled) count += match.groupValues[1].toInt() * match.groupValues[2].toInt()
        }
      }
      count
    }
  }

  // Read the input from the `src/Day01.txt` file.
  val input = readInput("day_3")
  part1(input).println()
  part2(input).println()
}
