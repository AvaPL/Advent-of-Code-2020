package io.github.avapl
package day7

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Bag]](day = 7){

  override protected def parse(string: String): List[Bag] =
    string.splitLines.map(parseBag)

  private def parseBag(line: String) =
    line match {
      case s"$name bags contain $innerBagsString." =>
        val innerBags = parseInnerBags(innerBagsString)
        Bag(name, innerBags)
    }

  private def parseInnerBags(innerBagsString: String) =
    innerBagsString.splitBy(", ").flatMap {
      case "no other bags" => None
      case s"$count $innerBagName bag$_" => Some(innerBagName -> count.toInt)
    }.toMap
}
