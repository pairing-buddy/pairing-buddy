import sbt.Keys._

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(
    scalaVersion := Settings.versions.scala,
    libraryDependencies ++= Settings.sharedDependencies.value
  )
  .jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm

lazy val sharedJs = shared.js

lazy val frontend: Project = (project in file("frontend"))
  .settings(
    name := s"${Settings.name}-frontend",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.scalajsDependencies.value
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb, WorkbenchPlugin)
  .dependsOn(sharedJs)

lazy val backend = (project in file("backend"))
  .settings(
    name := s"${Settings.name}-backend",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value,
    scalaJSProjects := Seq(frontend)
  )
  .dependsOn(sharedJvm)

onLoad in Global := (Command.process("project frontend", _: State)) compose (onLoad in Global).value
