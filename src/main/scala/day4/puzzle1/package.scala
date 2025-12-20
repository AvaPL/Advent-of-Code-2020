package io.github.avapl
package day4.puzzle1

import PassportField.CountryId

enum PassportField {
  case BirthYear, IssueYear, ExpirationYear, Height, HairColor, EyeColor, PassportId, CountryId
}

case class Passport(
    fields: Map[PassportField, String]
) {

  lazy val isValid: Boolean =
    requiredFields.forall(fields.contains)

  private lazy val requiredFields =
    PassportField.values.toSet - CountryId
}
