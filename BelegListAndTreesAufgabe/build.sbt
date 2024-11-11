ThisBuild / version := "1.0.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "BelegListAndTreeAufgabe",
    scalaVersion := "3.5.2",
    
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
