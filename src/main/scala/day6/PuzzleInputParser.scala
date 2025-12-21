package io.github.avapl
package day6

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[GroupAnswers]](day = 6){

  override protected def parse(string: String): List[GroupAnswers] =
    string.splitBlocks.map(parseGroupAnswers)
    
  private def parseGroupAnswers(block: String) =
    block.splitLines.map(parsePersonAnswers)
    
  private def parsePersonAnswers(line: String) =
    line.toSet
}
