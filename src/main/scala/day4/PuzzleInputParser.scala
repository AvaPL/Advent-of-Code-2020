package io.github.avapl
package day4

import day4.PassportField.*
import util.InputParser
import util.StringOps.*

object PuzzleInputParser extends InputParser[List[Passport]](day = 4) {

  override protected def parse(string: String): List[Passport] =
    string.splitBlocks.map(parsePassport)

  private def parsePassport(block: String) = {
    val fields = block.splitByRegex("[ \n]").map(parsePassportField).toMap
    Passport(fields)
  }

  private def parsePassportField(string: String) =
    string match {
      case s"$key:$value" =>
        val field = key match {
          case "byr" => BirthYear
          case "iyr" => IssueYear
          case "eyr" => ExpirationYear
          case "hgt" => Height
          case "hcl" => HairColor
          case "ecl" => EyeColor
          case "pid" => PassportId
          case "cid" => CountryId
        }
        field -> value
    }
}
