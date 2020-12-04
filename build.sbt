lazy val advent = project.in(file("."))
  .settings(
    name := "advent",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % "2.4.6",
      "org.tpolecat" %% "atto-core" % "0.8.0"
    )
  )

Global / onChangedBuildSource := ReloadOnSourceChanges
