package io.github.avapl
package day4.puzzle2

import util.StringOps.*

import cats.data.{NonEmptyList, Validated, ValidatedNel}
import cats.syntax.all.*

type ParsingError = String
type ValidationError = String

case class ApiPassport(
    birthYear: String,
    issueYear: String,
    expirationYear: String,
    height: String,
    hairColor: String,
    eyeColor: String,
    passportId: String,
    countryId: Option[String]
)

object ApiPassport {

  def fromString(string: String): ValidatedNel[ParsingError, ApiPassport] =
    toKeyValue(string).andThen(toApiPassport)

  private def toKeyValue(string: String): ValidatedNel[ParsingError, Map[String, String]] =
    string.trim
      .splitByRegex("[ \n]")
      .map {
        case s"$key:$value" => (key -> value).validNel
        case other          => s"'$other' couldn't be interpreted as <key>:<value>".invalidNel
      }
      .sequence
      .map(_.toMap)

  private def toApiPassport(keyValue: Map[String, String]): ValidatedNel[ParsingError, ApiPassport] =
    (
      keyValue.get("byr").toValidNel("Couldn't find birth year (byr)"),
      keyValue.get("iyr").toValidNel("Couldn't find issue year (iyr)"),
      keyValue.get("eyr").toValidNel("Couldn't find expiration year (eyr)"),
      keyValue.get("hgt").toValidNel("Couldn't find height (hgt)"),
      keyValue.get("hcl").toValidNel("Couldn't find hair color (hcl)"),
      keyValue.get("ecl").toValidNel("Couldn't find eye color (ecl)"),
      keyValue.get("pid").toValidNel("Couldn't find password ID (pid)"),
      keyValue.get("cid").validNel
    ).mapN(ApiPassport.apply)
}

opaque type BirthYear = Int

object BirthYear {

  private val yearFormat = "(\\d{4})".r
  private val minYear = 1920
  private val maxYear = 2002

  def fromString(string: String): ValidatedNel[ValidationError, BirthYear] =
    string match {
      case yearFormat(yearString) =>
        val year = yearString.toInt
        if (minYear <= year && year <= maxYear) year.validNel
        else s"Birth year must be in range [$minYear, $maxYear], was: $year".invalidNel
      case other => s"Birth year must be 4 digits, was: $other".invalidNel
    }
}

opaque type IssueYear = Int

object IssueYear {

  private val yearFormat = "(\\d{4})".r
  private val minYear = 2010
  private val maxYear = 2020

  def fromString(string: String): ValidatedNel[ValidationError, IssueYear] =
    string match {
      case yearFormat(yearString) =>
        val year = yearString.toInt
        if (minYear <= year && year <= maxYear) year.validNel
        else s"Issue year must be in range [$minYear, $maxYear], was: $year".invalidNel
      case other => s"Issue year must be 4 digits, was: $other".invalidNel
    }
}

opaque type ExpirationYear = Int

object ExpirationYear {

  private val yearFormat = "(\\d{4})".r
  private val minYear = 2020
  private val maxYear = 2030

  def fromString(string: String): ValidatedNel[ValidationError, ExpirationYear] =
    string match {
      case yearFormat(yearString) =>
        val year = yearString.toInt
        if (minYear <= year && year <= maxYear) year.validNel
        else s"Expiration year must be in range [$minYear, $maxYear], was: $year".invalidNel
      case other => s"Expiration year must be 4 digits, was: $other".invalidNel
    }
}

case class Height(value: Int, unit: Height.Unit)

object Height {

  enum Unit {
    case Centimeter, Inch
  }

  object Unit {

    def fromString(string: String): ValidatedNel[String, Unit] =
      string match {
        case "cm"  => Centimeter.validNel
        case "in"  => Inch.validNel
        case other => s"Unknown height unit: $other".invalidNel
      }
  }

  private val heightFormat = "(\\d+)(cm|in)".r
  private val minHeightCentimeters = 150
  private val maxHeightCentimeters = 193
  private val minHeightInches = 59
  private val maxHeightInches = 76

  def fromString(string: String): ValidatedNel[ValidationError, Height] =
    string match {
      case heightFormat(numberString, unitString) =>
        val number = numberString.toInt
        Unit.fromString(unitString).andThen {
          case unit @ Unit.Centimeter if minHeightCentimeters <= number && number <= maxHeightCentimeters =>
            Height(number, unit).validNel
          case unit @ Unit.Inch if minHeightInches <= number && number <= maxHeightInches =>
            Height(number, unit).validNel
          case Unit.Centimeter =>
            s"Height in centimeters must be in range [$minHeightCentimeters, $maxHeightCentimeters], was: $number".invalidNel
          case Unit.Inch =>
            s"Height in inches must be in range [$minHeightInches, $maxHeightInches], was: $number".invalidNel
        }
      case other =>
        s"Height must be a number followed by either 'cm' or 'in', was: $other".invalidNel
    }
}

opaque type HairColor = Int

object HairColor {

  private val hairColorFormat = "#([0-9a-f]{6})".r

  def fromString(string: String): ValidatedNel[String, HairColor] =
    string match {
      case hairColorFormat(hexString) => BigInt(hexString, radix = 16).toInt.validNel
      case other                      =>
        s"Hair color must be a # followed by six characters 0-9 or a-f, was: $other".invalidNel
    }
}

enum EyeColor {
  case Amber, Blue, Brown, Gray, Green, Hazel, Other
}

object EyeColor {

  def fromString(string: String): ValidatedNel[String, EyeColor] =
    string match {
      case "amb" => Amber.validNel
      case "blu" => Blue.validNel
      case "brn" => Brown.validNel
      case "gry" => Gray.validNel
      case "grn" => Green.validNel
      case "hzl" => Hazel.validNel
      case "oth" => Other.validNel
      case other => s"Unknown eye color: $other".invalidNel
    }
}

opaque type PassportId = String

object PassportId {

  private val passportIdFormat = "(\\d{9})".r

  def fromString(string: String): ValidatedNel[ValidationError, PassportId] =
    string match {
      case passportIdFormat(passportId) => passportId.validNel
      case other                        => s"Passport ID must be a nine-digit number, was: $other".invalidNel
    }
}

opaque type CountryId = String

object CountryId {

  def fromString(string: String): ValidatedNel[ValidationError, CountryId] =
    string.validNel
}

case class Passport(
    birthYear: BirthYear,
    issueYear: IssueYear,
    expirationYear: ExpirationYear,
    height: Height,
    hairColor: HairColor,
    eyeColor: EyeColor,
    passportId: PassportId,
    countryId: Option[CountryId]
)

object Passport {

  def fromApiPassport(apiPassport: ApiPassport): ValidatedNel[ValidationError, Passport] =
    (
      BirthYear.fromString(apiPassport.birthYear),
      IssueYear.fromString(apiPassport.issueYear),
      ExpirationYear.fromString(apiPassport.expirationYear),
      Height.fromString(apiPassport.height),
      HairColor.fromString(apiPassport.hairColor),
      EyeColor.fromString(apiPassport.eyeColor),
      PassportId.fromString(apiPassport.passportId),
      apiPassport.countryId.traverse(CountryId.fromString)
    ).mapN(Passport.apply)
}
