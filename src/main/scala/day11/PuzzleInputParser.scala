package io.github.avapl
package day11

import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[Map[Position, SeatState]](day = 11) {

  override protected def parse(string: String): Map[Position, SeatState] = {
    for {
      (line, row) <- string.splitLines.zipWithIndex
      (char, column) <- line.zipWithIndex
      if char != '.'
    } yield {
      val position = Position(row, column)
      val seatState = char match {
        case 'L' => SeatState.Empty
        case '#' => SeatState.Occupied
      }
      position -> seatState
    }
  }.toMap
}
