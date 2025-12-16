package io.github.avapl
package day3

import day3.GridElement.{EmptySquare, Tree}
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[Grid](day = 3) {

  override protected def parse(string: String): Grid = {
    val gridElements = for {
      line <- string.splitLines.toVector
    } yield
      for {
        char <- line.toVector
      } yield parseGridElement(char)
    Grid(gridElements)
  }

  private def parseGridElement(char: Char) =
    char match {
      case '.' => EmptySquare
      case '#' => Tree
    }
}
