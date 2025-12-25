package io.github.avapl
package day11

@main def puzzle1(): Unit = {
  val seats = PuzzleInputParser.parsedInput
  val stabilizedSeats = stabilize(seats, applyRulesRound1)
  val result = stabilizedSeats.values.count(_ == SeatState.Occupied)
  println(result)
}

private def applyRulesRound1(seats: Map[Position, SeatState]) =
  seats.map { (position, state) =>
    val adjacentOccupied = Direction.values.map(position + _).count(seats.get(_).contains(SeatState.Occupied))
    val newState =
      if (state == SeatState.Empty && adjacentOccupied == 0) SeatState.Occupied
      else if (state == SeatState.Occupied && adjacentOccupied >= 4) SeatState.Empty
      else state
    position -> newState
  }
