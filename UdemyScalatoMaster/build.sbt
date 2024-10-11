ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "UdemyScalatoMaster",
    Compile / scalacOptions ++= Seq("-deprecation"),
    Compile / console / scalacOptions --= Seq("-Ywarn-unused", "-Ywarn-unused-import"),
    Test / fork := true,
    libraryDependencies ++=Seq("org.scalactic" %% "scalactic" % "3.2.19" % "test",
      "org.scalatest" %% "scalatest" % "3.2.19" % "test"	)
  )
