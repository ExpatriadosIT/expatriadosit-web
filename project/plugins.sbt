resolvers ++= Seq(
  // for scala-ssh
  "spray repo" at "http://repo.spray.io",
  // sbt-twirl transitively depends on akka-actor right now...
  "typesafe releases" at "http://repo.typesafe.com/typesafe/releases/"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.1")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")
