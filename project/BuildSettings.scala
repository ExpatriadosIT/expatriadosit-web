import sbt._
import Keys._
import java.text.SimpleDateFormat
import java.util.Date
import sbtassembly.Plugin._
import AssemblyKeys._

object BuildSettings {
  val VERSION = "0.0.1"

  lazy val basicSettings = Seq(
    version               := VERSION + new SimpleDateFormat("-yyyyMMdd").format(new Date),
    homepage              := Some(new URL("http://expatriadosit.wikispaces.com/")),
    organization          := "io.spray",
    organizationHomepage  := Some(new URL("http://expatriadosit.wikispaces.com/")),
    description           := "ExpatriadosIT web",
    startYear             := Some(2013),
    licenses              := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion          := "2.10.2",
    resolvers             ++= Dependencies.resolutionRepos,
    scalacOptions         := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.6",
      "-language:postfixOps",
      "-language:implicitConversions",
      "-Xlog-reflective-calls",
      "-Ywarn-adapted-args"
    )
  )

  lazy val projectSettings = basicSettings ++ assemblySettings

}
