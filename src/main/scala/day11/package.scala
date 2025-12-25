package io.github.avapl
package day11

import scala.annotation.tailrec

case class Position(
    row: Int,
    column: Int
) {
  def +(direction: Direction): Position =
    copy(row + direction.row, column + direction.column)
}

enum SeatState {
  case Empty, Occupied
}

enum Direction(val row: Int, val column: Int) {
  case UpLeft extends Direction(row = -1, column = -1)
  case Up extends Direction(row = -1, column = 0)
  case UpRight extends Direction(row = -1, column = 1)
  case Left extends Direction(row = 0, column = -1)
  case Right extends Direction(row = 0, column = 1)
  case DownLeft extends Direction(row = 1, column = -1)
  case Down extends Direction(row = 1, column = 0)
  case DownRight extends Direction(row = 1, column = 1)
}

@tailrec
def stabilize(
    seats: Map[Position, SeatState],
    applyRulesRound: Map[Position, SeatState] => Map[Position, SeatState]
): Map[Position, SeatState] = {
  val afterRound = applyRulesRound(seats)
  if (afterRound == seats) seats
  else stabilize(afterRound, applyRulesRound)
}
