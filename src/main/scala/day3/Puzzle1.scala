package io.github.avapl
package day3

@main def puzzle1(): Unit = {
  val grid = PuzzleInputParser.parsedInput
  val result = countTrees(grid)(rowIncrement = 1, columnIncrement = 3)
  println(result)
}
