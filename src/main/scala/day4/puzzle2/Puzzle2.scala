package io.github.avapl
package day4.puzzle2

@main def puzzle2(): Unit = {
  val apiPassports = PuzzleInputParser.parsedInput
  val result = apiPassports.map(Passport.fromApiPassport).count(_.isValid)
  println(result)
}
