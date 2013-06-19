import sbt._
import Keys._


object Build extends Build {
  import BuildSettings._
  import Dependencies._

  // configure prompt to show current project
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Root Project
  // -------------------------------------------------------------------------------------------------------------------

  lazy val root = Project("expatriadosit",file("."))
    .settings(basicSettings: _*)
    .aggregate(expats_common, expats_rest)

  lazy val expats_common = Project("expats-common", file("expats-common"))
    .settings(projectSettings: _*)
    .settings(libraryDependencies ++=
    compile(rMongo, sprayJson, time, logging) ++
      test(specs2) ++
      provided(logback)
  )

  lazy val expats_rest = Project("expats-rest", file("expats-rest"))
    .settings(projectSettings: _*)
    .settings(libraryDependencies ++=
      compile(akkaActor, sprayCan, sprayRouting, sprayJson, logging) ++
      test(specs2) ++
      provided(akkaSlf4j, logback)
    )
    .dependsOn(expats_common)
}
