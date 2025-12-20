package io.github.avapl
package day4.puzzle1

@main def puzzle1(): Unit = {
  val passports = PuzzleInputParser.parsedInput
  val result = passports.count(_.isValid)
  println(result)
}