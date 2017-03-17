import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {
  val name    = "pairing-buddy"
  val version = "0.1.0"

  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  object versions {
    val scala    = "2.12.1"
    val scalaDom = "0.9.1"
  }

  val sharedDependencies = Def.setting(
    Seq(
      )
  )

  val jvmDependencies = Def.setting(
    Seq(
      )
  )

  val scalajsDependencies = Def.setting(
    Seq(
      "org.scala-js" %%% "scalajs-dom" % versions.scalaDom
    )
  )
}
