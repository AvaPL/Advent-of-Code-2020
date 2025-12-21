package io.github.avapl
package day7

import scala.collection.mutable

@main def puzzle1(): Unit = {
  val bags = PuzzleInputParser.parsedInput
  val result = countPossibleOuterBags(bags)
  println(result)
}

private def countPossibleOuterBags(bags: List[Bag]) = {
  val bagNameToInnerBagNames = bags.map(bag => bag.name -> bag.innerBags.keySet).toMap
  val possibleOuterBagNames = mutable.Set.empty[String]

  def loop(currentBagName: String): Boolean =
    if (possibleOuterBagNames.contains(currentBagName))
      true // Path already evaluated
    else if (
      bagNameToInnerBagNames(currentBagName).contains(myBagName) ||
      bagNameToInnerBagNames(currentBagName).exists(loop)
    ) {
      possibleOuterBagNames.add(currentBagName)
      true
    } else false

  bags.map(_.name).foreach(loop)
  possibleOuterBagNames.size
}
