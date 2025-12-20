package io.github.avapl
package day5

@main def puzzle1(): Unit = {
  val boardingPasses = PuzzleInputParser.parsedInput
  val result = boardingPasses.map(_.id).max
  println(result)
}