package io.github.avapl
package day4.puzzle2

import util.InputParser
import util.StringOps.*

import cats.data.Validated

object PuzzleInputParser extends InputParser[List[ApiPassport]](day = 4) {

  override protected def parse(string: String): List[ApiPassport] =
    string.splitBlocks.map(ApiPassport.fromString).collect {
      case Validated.Valid(validApiPassport) => validApiPassport
    }
}
