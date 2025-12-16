package io.github.avapl
package day2.puzzle2

import day2.Password

import scala.util.chaining.*

@main def puzzle2(): Unit = {
  val passwordPolicyAndPassword = PuzzleInputParser.parsedInput
  val result = passwordPolicyAndPassword.count(isValid)
  println(result)
}

private def isValid(password: Password, passwordPolicy: PasswordPolicy) =
  password.lift(passwordPolicy.position1 - 1).contains(passwordPolicy.letter) ^
    password.lift(passwordPolicy.position2 - 1).contains(passwordPolicy.letter)
