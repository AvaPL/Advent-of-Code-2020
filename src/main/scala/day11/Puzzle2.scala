package io.github.avapl
package day11

import scala.annotation.tailrec

@main def puzzle2(): Unit = {
  val seats = PuzzleInputParser.parsedInput
  val stabilizedSeats = stabilize(
    seats,
    applyRulesRound = seats => {
      val positionToVisibleSeatPositions = determineVisibleSeatPositions(seats)
      applyRulesRound2(positionToSeatState = seats, positionToVisibleSeatPositions)
    }
  )
  val result = stabilizedSeats.values.count(_ == SeatState.Occupied)
  println(result)
}

private def determineVisibleSeatPositions(seats: Map[Position, SeatState]) = {
  val positions = seats.keySet
  val maxRow = positions.map(_.row).max
  val maxColumn = positions.map(_.column).max

  @tailrec
  def nextVisible(position: Position)(direction: Direction): Option[Position] = {
    val nextPosition = position + direction
    if (
      0 <= nextPosition.row &&
      nextPosition.row <= maxRow &&
      0 <= nextPosition.column &&
      nextPosition.column <= maxColumn
    ) {
      if (positions.contains(nextPosition)) Some(nextPosition)
      else nextVisible(nextPosition)(direction)
    } else None
  }

  positions.toList.map { position =>
    val visiblePositions = Direction.values.flatMap(nextVisible(position)).toList
    position -> visiblePositions
  }.toMap
}

private def applyRulesRound2(
    positionToSeatState: Map[Position, SeatState],
    positionToVisibleSeatPositions: Map[Position, List[Position]]
) =
  positionToSeatState.map { (position, state) =>
    val visibleOccupied = positionToVisibleSeatPositions(position)
      .map(positionToSeatState)
      .count(_ == SeatState.Occupied)
    val newState =
      if (state == SeatState.Empty && visibleOccupied == 0) SeatState.Occupied
      else if (state == SeatState.Occupied && visibleOccupied >= 5) SeatState.Empty
      else state
    position -> newState
  }
