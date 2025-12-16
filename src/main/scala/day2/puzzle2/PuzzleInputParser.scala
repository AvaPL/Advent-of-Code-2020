package io.github.avapl
package day2.puzzle2

import day2.Password
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[(Password, PasswordPolicy)]](day = 2) {

  override protected def parse(string: Password): List[(Password, PasswordPolicy)] =
    string.splitLines.map {
      case s"$position1-$position2 $char: $password" =>
        val passwordPolicy = PasswordPolicy(char.head, position1.toInt, position2.toInt)
        (password, passwordPolicy)
    }
}
