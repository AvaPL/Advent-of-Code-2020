package io.github.avapl
package day5

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[BoardingPass]](day = 5){

  override protected def parse(string: String): List[BoardingPass] =
    string.splitLines.map(BoardingPass.apply)
}
