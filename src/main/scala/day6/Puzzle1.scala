package io.github.avapl
package day6

@main def puzzle1(): Unit = {
  val groupsAnswers = PuzzleInputParser.parsedInput
  val result = groupsAnswers.map(_.flatten.toSet.size).sum
  println(result)
}