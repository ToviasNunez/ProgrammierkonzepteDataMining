ThisBuild / version := "1.0.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "Uebung1",
    scalaVersion := "3.5.0",
    Compile / scalacOptions ++= Seq("-deprecation"),
    Compile / console / scalacOptions --= Seq("-Ywarn-unused", "-Ywarn-unused-import"),
    Test / fork := true,
    libraryDependencies ++=Seq("org.scalactic" %% "scalactic" % "3.2.19" % "test",
	"org.scalatest" %% "scalatest" % "3.2.19" % "test"	)

  )
  
