package io.github.avapl
package day10

@main def puzzle1(): Unit = {
  val joltages = PuzzleInputParser.parsedInput
  val sortedJoltages = joltages.sorted
  val myJoltage = sortedJoltages.last + 3
  val allJoltages = sortedJoltages :+ myJoltage
  val differencesFromSource = (sourceJoltage +: allJoltages)
    .sliding(2)
    .map {
      case Vector(a, b) => b - a
    }
    .toVector
  val oneJoltDifferences = differencesFromSource.count(_ == 1)
  val threeJoltDifferences = differencesFromSource.count(_ == 3)
  val result = oneJoltDifferences * threeJoltDifferences
  println(result)
}
