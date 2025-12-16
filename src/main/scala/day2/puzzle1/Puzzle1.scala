package io.github.avapl
package day2.puzzle1

import day2.Password

import scala.util.chaining.*

@main def puzzle1(): Unit = {
  val passwordPolicyAndPassword = PuzzleInputParser.parsedInput
  val result = passwordPolicyAndPassword.count(isValid)
  println(result)
}

private def isValid(password: Password, passwordPolicy: PasswordPolicy) =
  password
    .count(_ == passwordPolicy.letter)
    .pipe(isBetween(passwordPolicy.minOccurrences, passwordPolicy.maxOccurrences))

private def isBetween(min: Int, max: Int)(value: Int) =
  min <= value && value <= max
