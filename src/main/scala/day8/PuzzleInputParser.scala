package io.github.avapl
package day8

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[Vector[Instruction]](day = 8) {

  override protected def parse(string: String): Vector[Instruction] =
    string.splitLines.map(parseInstruction).toVector
    
  private def parseInstruction(line: String) =
    line match {
      case s"$operation $argument" =>
        Instruction(Operation.valueOf(operation), argument.toInt)
    }
}
