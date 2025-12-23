package io.github.avapl
package day9

import scala.annotation.tailrec

@main def puzzle1(): Unit = {
  val numbers = PuzzleInputParser.parsedInput
  val result = findFirstViolatingRules(numbers, preambleLength = 25)
  println(result)
}

private def findSumOfTwo(numbers: Vector[Long], sum: Long): Option[(Long, Long)] = {

  @tailrec
  def loop(sortedNumbers: Vector[Long]): Option[(Long, Long)] =
    sortedNumbers match {
      case Vector() | Vector(_) => None
      case a +: _ :+ b          =>
        if (a + b < sum)
          loop(sortedNumbers.drop(1))
        else if (a + b > sum)
          loop(sortedNumbers.dropRight(1))
        else // (a + b == sum)
          Some((a, b))
    }

  val sortedNumbers = numbers.sorted
  loop(sortedNumbers)
}
