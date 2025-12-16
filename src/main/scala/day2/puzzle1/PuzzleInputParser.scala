package io.github.avapl
package day2.puzzle1

import day2.Password
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[(Password, PasswordPolicy)]](day = 2) {

  override protected def parse(string: Password): List[(Password, PasswordPolicy)] =
    string.splitLines.map {
      case s"$minOccurrences-$maxOccurrences $char: $password" =>
        val passwordPolicy = PasswordPolicy(char.head, minOccurrences.toInt, maxOccurrences.toInt)
        (password, passwordPolicy)
    }
}
