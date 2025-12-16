package io.github.avapl
package day3

@main def puzzle2(): Unit = {
  val grid = PuzzleInputParser.parsedInput
  val slopes = List(
    (1, 1),
    (1, 3),
    (1, 5),
    (1, 7),
    (2, 1)
  )
  val treeCounts = slopes.map(countTrees(grid))
  val result = treeCounts.product
  println(result)
}
