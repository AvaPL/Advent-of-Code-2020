package io.github.avapl
package day5

case class BoardingPass(value: String) {

  lazy val id: Int = BigInt(
    value.map {
      case 'F' => '0'
      case 'B' => '1'
      case 'L' => '0'
      case 'R' => '1'
    },
    radix = 2
  ).toInt
}
