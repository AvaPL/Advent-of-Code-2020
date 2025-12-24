package io.github.avapl
package day10

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[Vector[Joltage]](day = 10) {

  override protected def parse(string: String): Vector[Joltage] =
    string.splitLines.map(_.toInt).toVector
}
