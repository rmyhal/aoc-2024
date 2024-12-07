fun main() {

  fun part1(input: List<String>): Int {
    val rules = input.takeWhile { it.isNotBlank() }
      .groupBy(
        keySelector = { it.split('|').first().toInt() },
        valueTransform = { it.split('|').last().toInt() }
      )
      .mapValues { it.value.toSet() }

    val updatesList = input.takeLastWhile { it.isNotBlank() }
      .map { it.split(',').map { u -> u.toInt() } }

    return updatesList.sumOf { update ->
      for (i in update.size - 1 downTo 1) {
        val page = update[i]

        for (y in i - 1 downTo 0) {
          if (!rules.containsKey(update[y])) {
            return@sumOf 0
          }
          val previousPage = rules.getValue((update[y]))
          if (!previousPage.contains(page)) {
            return@sumOf 0
          }
        }
      }
      update[update.size / 2]
    }
  }

  fun part2(input: List<String>): Int {
    val rules = input.takeWhile { it.isNotBlank() }
      .groupBy(
        keySelector = { it.split('|').first().toInt() },
        valueTransform = { it.split('|').last().toInt() }
      )
      .mapValues { it.value.toSet() }

    val updatesList = input.takeLastWhile { it.isNotBlank() }
      .map { it.split(',').map { u -> u.toInt() } }

    return updatesList.sumOf { update ->
      for (i in update.size - 1 downTo 1) {
        val page = update[i]

        for (y in i - 1 downTo 0) {
          if (!rules.containsKey(update[y])) {
            val sorted = update.sortedWith { o1, o2 ->
              if (rules[o1]?.contains(o2) == true) -1 else 1
            }
            return@sumOf sorted[sorted.size / 2]
          }
          val previousPage = rules.getValue((update[y]))
          if (!previousPage.contains(page)) {
            // fix
            val sorted = update.sortedWith { o1, o2 ->
              if (rules[o1]?.contains(o2) == true) -1 else 1
            }
            return@sumOf sorted[sorted.size / 2]
          }
        }
      }
      val wtf = 0
      return@sumOf wtf
    }
  }

  val input = readInput("day_5")
  part1(input).println()
  part2(input).println()
}
