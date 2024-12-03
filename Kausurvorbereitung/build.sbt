import sbt.Keys.scalaVersion

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.1"

lazy val root = (project in file("."))
  .settings(
    name := "Kausurvorbereitung",

// Compiler options
Compile / scalacOptions ++= Seq("-deprecation"),

// Dependencies
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.17.1",
  "org.scalactic" %% "scalactic" % "3.2.19" % Test,
  "org.scalatest" %% "scalatest" % "3.2.19" % Test
),

// Overrides for compatibility
dependencyOverrides += "org.scala-sbt" % "compiler-bridge_3" % "1.9.2",

// Enable forked JVM for tests
Test / fork := true
  )
