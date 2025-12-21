package io.github.avapl
package day7

import scala.collection.mutable

@main def puzzle2(): Unit = {
  val bags = PuzzleInputParser.parsedInput
  val result = countInnerBags(bags)
  println(result)
}

private def countInnerBags(bags: List[Bag]) = {
  val bagNameToBag = bags.map(bag => bag.name -> bag).toMap
  val bagNameToCount = mutable.Map.empty[String, Int]

  def loop(currentBagName: String): Int =
    bagNameToCount.getOrElseUpdate(
      currentBagName,
      1 + bagNameToBag(currentBagName).innerBags.map { (innerBagName, count) =>
        loop(innerBagName) * count
      }.sum
    )

  loop(myBagName) - 1
}
