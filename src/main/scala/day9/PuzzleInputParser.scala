package io.github.avapl
package day9

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[Vector[Long]](day = 9){

  override protected def parse(string: String): Vector[Long] =
    string.splitLines.map(_.toLong).toVector
}
