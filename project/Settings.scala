import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {
  val name    = "pairing-buddy"
  val version = "0.1.0"

  val scalacOptions = Seq(
    "-Xlint",
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:postfixOps",
    "-Xfuture",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Ywarn-unused"
  )

  object v {
    val scala          = "2.12.1"
    val scalaDom       = "0.9.1"
    val akkaHttp       = "10.0.5"
    val scalajsScripts = "1.1.0"
    val config         = "1.3.1"
  }

  val sharedDependencies = Def.setting(
    Seq(
      )
  )

  val jvmDependencies = Def.setting(
    Seq(
      "com.typesafe.akka" %% "akka-http"       % v.akkaHttp,
      "com.vmunier"       %% "scalajs-scripts" % v.scalajsScripts,
      "com.typesafe"      % "config"           % v.config
    )
  )

  val scalajsDependencies = Def.setting(
    Seq(
      "org.scala-js" %%% "scalajs-dom" % v.scalaDom
    )
  )
}
