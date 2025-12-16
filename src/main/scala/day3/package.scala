package io.github.avapl
package day3

import day3.GridElement.Tree

enum GridElement {
  case Tree, EmptySquare
}

case class Grid(elements: Vector[Vector[GridElement]]) {

  val height: Int = elements.size

  def apply(row: Int, column: Int): GridElement = {
    val rowElements = elements(row)
    rowElements(column % rowElements.size)
  }
}

def countTrees(grid: Grid)(rowIncrement: Int, columnIncrement: Int) = {
  var row = rowIncrement
  var column = columnIncrement
  var treeCount = 0L

  while (row < grid.height) {
    if (grid(row, column) == Tree)
      treeCount += 1
    row += rowIncrement
    column += columnIncrement
  }

  treeCount
}
