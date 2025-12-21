package io.github.avapl
package day6

@main def puzzle2(): Unit = {
  val groupsAnswers = PuzzleInputParser.parsedInput
  val result = groupsAnswers.map(_.reduce( _ & _).size).sum
  println(result)
}