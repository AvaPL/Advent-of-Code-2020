package io.github.avapl
package day8

@main def puzzle1(): Unit = {
  val instructions = PuzzleInputParser.parsedInput
  val (result, _) = runOneLoopIteration(instructions)
  println(result)
}
