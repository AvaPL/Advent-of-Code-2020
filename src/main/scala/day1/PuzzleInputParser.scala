package io.github.avapl
package day1

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Int]](day = 1){

  override protected def parse(string: String): List[Int] =
    string.splitLines.map(_.toInt)
}
