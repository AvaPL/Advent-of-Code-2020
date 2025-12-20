package io.github.avapl
package day5

@main def puzzle2(): Unit = {
  val boardingPasses = PuzzleInputParser.parsedInput
  val firstBoardingPass = findFirst(boardingPasses)
  val result = findFirstMissingId(boardingPasses, after = firstBoardingPass)
  println(result)
}

private def findFirst(boardingPasses: List[BoardingPass]) =
  boardingPasses.minBy(_.id)

private def findFirstMissingId(boardingPasses: List[BoardingPass], after: BoardingPass) = {
  val idsSet = boardingPasses.map(_.id)
  Iterator.from(after.id).find(!idsSet.contains(_)).get
}