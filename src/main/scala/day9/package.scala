package io.github.avapl
package day9

def findFirstViolatingRules(numbers: Vector[Long], preambleLength: Int) =
  numbers
    .sliding(preambleLength + 1)
    .collectFirst {
      case previous25 :+ sum if findSumOfTwo(previous25, sum).isEmpty => sum
    }
    .get
