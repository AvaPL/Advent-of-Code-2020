package io.github.avapl
package day10

import scala.collection.mutable
import scala.util.chaining.*

@main def puzzle2(): Unit = {
  val joltages = PuzzleInputParser.parsedInput
  val myJoltage = joltages.max + 3
  val allJoltages = joltages :+ myJoltage
  val result = countCombinations(allJoltages)
  println(result)
}

private def countCombinations(joltages: Vector[Joltage]) = {
  val sourceJoltageToCombinationsCount = mutable.Map.empty[Joltage, Long]

  def loop(sourceJoltage: Joltage): Long =
    sourceJoltageToCombinationsCount.getOrElseUpdate(
      sourceJoltage,
      joltages
        .filter(joltage => (1 to 3).contains(joltage - sourceJoltage))
        .pipe { nextPossibleJoltages =>
          if (nextPossibleJoltages.isEmpty) 1
          else nextPossibleJoltages.map(loop).sum
        }
    )

  loop(sourceJoltage)
}
