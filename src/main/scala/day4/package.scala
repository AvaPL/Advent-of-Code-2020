package io.github.avapl

package day4

import day4.PassportField.CountryId

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
