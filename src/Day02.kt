fun main() {

  fun isSafe(report: List<Int>): Boolean {
    var increasing = true
    for (i in 0 until report.size - 1) {
      if (i == 0) {
        // check direction
        increasing = report[1] > report[i]
      }
      if (report[i] == report[i + 1]) {
        return false
      }

      if (increasing) {
        if (report[i + 1] < report[i]) {
          return false
        }
        if (report[i + 1] - report[i] > 3) {
          return false
        }
      } else {
        if (report[i + 1] > report[i]) {
          return false
        }
        if (report[i] - report[i + 1] > 3) {
          return false
        }
      }
    }
    return true
  }

  fun part1(input: List<String>): Int {
    var answer = 0
    input.forEach { rawReport ->
      val report = rawReport.split(" ").map { it.toInt() }.toMutableList()
      if (isSafe(report)) answer += 1
    }

    return answer
  }

  fun part2(input: List<String>): Int {
    var answer = 0
    input.forEach { rawReport ->
      val report = rawReport.split(" ").map { it.toInt() }.toMutableList()
      var isSafe = isSafe(report)

      if (!isSafe) {
        // try removing one level
        var removed: Pair<Int, Int>? = null
        for (i in report.indices) {
          if (removed != null) {
            report.add(removed.first, removed.second)
          }
          removed = i to report.removeAt(i)
          isSafe = isSafe(report)
          if (isSafe) {
            break
          }
        }
      }

      if (isSafe) answer += 1
    }
    return answer
  }


  // Read the input from the `src/Day01.txt` file.
  val input = readInput("day_2")
  part1(input).println()
  part2(input).println()
}
