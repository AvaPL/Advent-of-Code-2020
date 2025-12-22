package io.github.avapl
package day8

@main def puzzle2(): Unit = {
  val instructions = PuzzleInputParser.parsedInput
  val result = correctInstructionsAndRun(instructions)
  println(result)
}

private def correctInstructionsAndRun(instructions: Vector[Instruction]) =
  instructions.zipWithIndex
    .collect {
      case (instruction @ Instruction(Operation.jmp, argument), i) =>
        instructions.updated(i, instruction.copy(operation = Operation.nop))
      case (instruction @ Instruction(Operation.nop, argument), i) =>
        instructions.updated(i, instruction.copy(operation = Operation.jmp))
    }
    .iterator
    .map(runOneLoopIteration)
    .collectFirst {
      case (accumulator, hasLoop) if !hasLoop => accumulator
    }
    .get
