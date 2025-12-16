package io.github.avapl
package day1

@main def puzzle2(): Unit = {
  val expenses = PuzzleInputParser.parsedInput
  val (expense1, expense2, expense3) = findSumOfThree2020(expenses)
  val result = expense1 * expense2 * expense3
  println(result)
}

private def findSumOfThree2020(expenses: List[Int]) = {
  val sortedExpenses = expenses.sorted.toVector

  def loop(sortedExpenses: Vector[Int], selectedExpense: Int): Option[(Int, Int, Int)] =
    sortedExpenses match {
      case a +: _ :+ b if a != selectedExpense && b != selectedExpense =>
        if (a + selectedExpense + b < 2020)
          loop(sortedExpenses.drop(1), selectedExpense)
        else if (a + selectedExpense + b > 2020)
          loop(sortedExpenses.dropRight(1), selectedExpense)
        else if (a + selectedExpense + b == 2020)
          Some((a, selectedExpense, b))
        else
          None
      case _ => None
    }

  sortedExpenses.iterator
    .map { selectedExpense =>
      loop(sortedExpenses, selectedExpense)
    }
    .find(_.isDefined)
    .get
    .get
}
