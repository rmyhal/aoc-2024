fun main() {
  fun StringBuilder.withBuilder(block: (StringBuilder) -> Unit): String {
    block(this)
    return toString().also { clear() }
  }

  fun part1(input: List<String>): Int {
    val words = mutableListOf<String>()
    for (y in input.indices) {
      for (x in 0 until input[y].length) {
        if (input[y][x] == 'X') {
          // run search

          // top y+1, y+2, y+3, x
          val builder = StringBuilder()
          var word = ""
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y+i)?.getOrNull(x))
            }
          }
          words.add(word)

          // diagonal, y+1, y+2, y+3, x+1, x+2, x+3
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y+i)?.getOrNull(x+i))
            }
          }
          words.add(word)

          // right y, x+1, x+2, x+3
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y)?.getOrNull(x+i))
            }
          }
          words.add(word)

          // diagonal y-1, y-2, y-3, x+1, x+2, x+3
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y-i)?.getOrNull(x+i))
            }
          }
          words.add(word)

          // bottom y-1, y-2, y-3, x
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y-i)?.getOrNull(x))
            }
          }
          words.add(word)

          // diagonal y-1, y-2, y-3, x-1, x-2, x-3
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y-i)?.getOrNull(x-i))
            }
          }
          words.add(word)

          // left y, x-1, x-2, x-3
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y)?.getOrNull(x-i))
            }
          }
          words.add(word)

          // diagonal y+1, y+2, y+3, x-1, x-2, x-3
          builder.append(input[y][x])
          word = builder.withBuilder { b ->
            for (i in 1 until 4) {
              b.append(input.getOrNull(y+i)?.getOrNull(x-i))
            }
          }
          words.add(word)
        }
      }
    }

    return words.count { it == "XMAS" }
  }


  fun part2(input: List<String>): Int {
    var count = 0
    for (y in input.indices) {
      for (x in 0 until input[y].length) {
        if (input[y][x] == 'A') {
          val builder = StringBuilder()
          val nw = builder.withBuilder { b ->
            for (i in -1 until 2) {
              b.append(input.getOrNull(y+i)?.getOrNull(x+i))
            }
          }
          val ne = builder.withBuilder { b ->
            for (i in 1 downTo -1) {
              b.append(input.getOrNull(y+i)?.getOrNull(x+(i * -1)))
            }
          }
          if ((nw == "SAM" || nw == "MAS") && (ne == "SAM" || ne == "MAS")) {
            count++
          }
        }
      }
    }
    return count
  }

  val input = readInput("day_4")
  part1(input).println()
  part2(input).println()
}
