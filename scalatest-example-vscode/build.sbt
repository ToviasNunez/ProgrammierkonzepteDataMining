lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.example",
      scalaVersion := "3.5.0"
    )
  ),
  name := "scalatest-example-vscode"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
