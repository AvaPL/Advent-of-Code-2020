package io.github.avapl
package day8

import scala.collection.mutable

enum Operation {
  case acc, jmp, nop
}

case class Instruction(
    operation: Operation,
    argument: Int
)

def runOneLoopIteration(instructions: Vector[Instruction]): (name: Int, hasLoop: Boolean) = {
  var currentInstructionIndex = 0
  val executedInstructionIndices = mutable.Set.empty[Int]
  var accumulator = 0

  while (!executedInstructionIndices.contains(currentInstructionIndex) && currentInstructionIndex < instructions.size) {
    executedInstructionIndices.addOne(currentInstructionIndex)
    val Instruction(operation, argument) = instructions(currentInstructionIndex)
    operation match {
      case Operation.nop =>
        currentInstructionIndex += 1
      case Operation.acc =>
        accumulator += argument
        currentInstructionIndex += 1
      case Operation.jmp =>
        currentInstructionIndex += argument
    }
  }

  val hasLoop = currentInstructionIndex != instructions.size
  (accumulator, hasLoop)
}
