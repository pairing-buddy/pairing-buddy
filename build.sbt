import sbt.Keys._

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(
    scalaVersion := Settings.v.scala,
    libraryDependencies ++= Settings.sharedDependencies.value
  )
  .jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm

lazy val sharedJs = shared.js

lazy val frontend: Project = (project in file("frontend"))
  .settings(
    name := s"${Settings.name}-frontend",
    version := Settings.version,
    scalaVersion := Settings.v.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.scalajsDependencies.value,
    scalaJSUseMainModuleInitializer := true
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(sharedJs)

lazy val backend = (project in file("backend"))
  .settings(
    name := s"${Settings.name}-backend",
    version := Settings.version,
    scalaVersion := Settings.v.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value,
    scalaJSProjects := Seq(frontend),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    compile in Compile <<= (compile in Compile) dependsOn scalaJSPipeline,
    WebKeys.packagePrefix in Assets := "public/",
    managedClasspath in Runtime += (packageBin in Assets).value,
    dockerBaseImage := "openjdk:8-jre-alpine",
    dockerExposedPorts := Seq(8082),
    dockerRepository in Docker := Some("asarturas"),
    packageName in Docker := "pairing-buddy",
    version in Docker := "latest"
  )
  .enablePlugins(SbtWeb, SbtTwirl, AshScriptPlugin, DockerPlugin)
  .dependsOn(sharedJvm)

onLoad in Global := (Command.process("project backend", _: State)) compose (onLoad in Global).value

addCommandAlias("verify", ";test")