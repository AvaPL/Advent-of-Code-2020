ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.4"

lazy val root = (project in file("."))
  .settings(
    name := "Advent-of-Code-2020",
    idePackagePrefix := Some("io.github.avapl"),
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.13.0"
  )
