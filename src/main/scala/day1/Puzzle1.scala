package io.github.avapl
package day1

@main def puzzle1(): Unit = {
  val expenses = PuzzleInputParser.parsedInput
  val (expense1, expense2) = findSumOfTwo2020(expenses)
  val result = expense1 * expense2
  println(result)
}

private def findSumOfTwo2020(expenses: List[Int]) = {
  val sortedExpenses = expenses.sorted.toVector

  def loop(sortedExpenses: Vector[Int]): (Int, Int) =
    sortedExpenses match {
      case a +: _ :+ b =>
        if (a + b < 2020)
          loop(sortedExpenses.drop(1))
        else if (a + b > 2020)
          loop(sortedExpenses.dropRight(1))
        else // (a + b == 2020)
          (a, b)
    }

  loop(sortedExpenses)
}
