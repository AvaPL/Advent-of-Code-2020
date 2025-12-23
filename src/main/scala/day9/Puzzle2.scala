package io.github.avapl
package day9

import scala.annotation.tailrec

@main def puzzle2(): Unit = {
  val numbers = PuzzleInputParser.parsedInput
  val invalidNumber = findFirstViolatingRules(numbers, preambleLength = 25)
  val contiguousSet = findContiguousSum(numbers, sum = invalidNumber)
  val sortedContiguousSet = contiguousSet.sorted
  val result = sortedContiguousSet.head + sortedContiguousSet.last
  println(result)
}

private def findContiguousSum(numbers: Vector[Long], sum: Long): Vector[Long] = {

  @tailrec
  def loop(from: Int, to: Int, acc: Long): Vector[Long] =
    if (acc < sum)
      loop(from, to + 1, acc + numbers(to + 1))
    else if (acc > sum)
      loop(from + 1, to, acc - numbers(from))
    else // (acc == sum)
      numbers.slice(from, until = to + 1)

  loop(from = 0, to = 1, acc = numbers.take(2).sum)
}
